import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IProjectBuilder, defaultValue } from 'app/shared/model/project-builder.model';

export const ACTION_TYPES = {
  FETCH_PROJECTBUILDER_LIST: 'projectBuilder/FETCH_PROJECTBUILDER_LIST',
  FETCH_PROJECTBUILDER: 'projectBuilder/FETCH_PROJECTBUILDER',
  CREATE_PROJECTBUILDER: 'projectBuilder/CREATE_PROJECTBUILDER',
  UPDATE_PROJECTBUILDER: 'projectBuilder/UPDATE_PROJECTBUILDER',
  DELETE_PROJECTBUILDER: 'projectBuilder/DELETE_PROJECTBUILDER',
  SET_BLOB: 'projectBuilder/SET_BLOB',
  RESET: 'projectBuilder/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProjectBuilder>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ProjectBuilderState = Readonly<typeof initialState>;

// Reducer

export default (state: ProjectBuilderState = initialState, action): ProjectBuilderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROJECTBUILDER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROJECTBUILDER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROJECTBUILDER):
    case REQUEST(ACTION_TYPES.UPDATE_PROJECTBUILDER):
    case REQUEST(ACTION_TYPES.DELETE_PROJECTBUILDER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROJECTBUILDER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROJECTBUILDER):
    case FAILURE(ACTION_TYPES.CREATE_PROJECTBUILDER):
    case FAILURE(ACTION_TYPES.UPDATE_PROJECTBUILDER):
    case FAILURE(ACTION_TYPES.DELETE_PROJECTBUILDER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROJECTBUILDER_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROJECTBUILDER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROJECTBUILDER):
    case SUCCESS(ACTION_TYPES.UPDATE_PROJECTBUILDER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROJECTBUILDER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.SET_BLOB:
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType
        }
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = SERVER_API_URL + '/api/project-builders';

// Actions

export const getEntities: ICrudGetAllAction<IProjectBuilder> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROJECTBUILDER_LIST,
    payload: axios.get<IProjectBuilder>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IProjectBuilder> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROJECTBUILDER,
    payload: axios.get<IProjectBuilder>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProjectBuilder> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROJECTBUILDER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProjectBuilder> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROJECTBUILDER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProjectBuilder> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROJECTBUILDER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType
  }
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
