import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IResidentialArea, defaultValue } from 'app/shared/model/residential-area.model';

export const ACTION_TYPES = {
  FETCH_RESIDENTIALAREA_LIST: 'residentialArea/FETCH_RESIDENTIALAREA_LIST',
  FETCH_RESIDENTIALAREA: 'residentialArea/FETCH_RESIDENTIALAREA',
  CREATE_RESIDENTIALAREA: 'residentialArea/CREATE_RESIDENTIALAREA',
  UPDATE_RESIDENTIALAREA: 'residentialArea/UPDATE_RESIDENTIALAREA',
  DELETE_RESIDENTIALAREA: 'residentialArea/DELETE_RESIDENTIALAREA',
  SET_BLOB: 'residentialArea/SET_BLOB',
  RESET: 'residentialArea/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IResidentialArea>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ResidentialAreaState = Readonly<typeof initialState>;

// Reducer

export default (state: ResidentialAreaState = initialState, action): ResidentialAreaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RESIDENTIALAREA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RESIDENTIALAREA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_RESIDENTIALAREA):
    case REQUEST(ACTION_TYPES.UPDATE_RESIDENTIALAREA):
    case REQUEST(ACTION_TYPES.DELETE_RESIDENTIALAREA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_RESIDENTIALAREA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RESIDENTIALAREA):
    case FAILURE(ACTION_TYPES.CREATE_RESIDENTIALAREA):
    case FAILURE(ACTION_TYPES.UPDATE_RESIDENTIALAREA):
    case FAILURE(ACTION_TYPES.DELETE_RESIDENTIALAREA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESIDENTIALAREA_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_RESIDENTIALAREA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_RESIDENTIALAREA):
    case SUCCESS(ACTION_TYPES.UPDATE_RESIDENTIALAREA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_RESIDENTIALAREA):
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

const apiUrl = SERVER_API_URL + '/api/residential-areas';

// Actions

export const getEntities: ICrudGetAllAction<IResidentialArea> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_RESIDENTIALAREA_LIST,
    payload: axios.get<IResidentialArea>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IResidentialArea> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RESIDENTIALAREA,
    payload: axios.get<IResidentialArea>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IResidentialArea> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RESIDENTIALAREA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IResidentialArea> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RESIDENTIALAREA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IResidentialArea> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RESIDENTIALAREA,
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
