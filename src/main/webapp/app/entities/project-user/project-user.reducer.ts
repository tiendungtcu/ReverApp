import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IProjectUser, defaultValue } from 'app/shared/model/project-user.model';

export const ACTION_TYPES = {
  FETCH_PROJECTUSER_LIST: 'projectUser/FETCH_PROJECTUSER_LIST',
  FETCH_PROJECTUSER: 'projectUser/FETCH_PROJECTUSER',
  CREATE_PROJECTUSER: 'projectUser/CREATE_PROJECTUSER',
  UPDATE_PROJECTUSER: 'projectUser/UPDATE_PROJECTUSER',
  DELETE_PROJECTUSER: 'projectUser/DELETE_PROJECTUSER',
  RESET: 'projectUser/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProjectUser>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ProjectUserState = Readonly<typeof initialState>;

// Reducer

export default (state: ProjectUserState = initialState, action): ProjectUserState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROJECTUSER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROJECTUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROJECTUSER):
    case REQUEST(ACTION_TYPES.UPDATE_PROJECTUSER):
    case REQUEST(ACTION_TYPES.DELETE_PROJECTUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROJECTUSER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROJECTUSER):
    case FAILURE(ACTION_TYPES.CREATE_PROJECTUSER):
    case FAILURE(ACTION_TYPES.UPDATE_PROJECTUSER):
    case FAILURE(ACTION_TYPES.DELETE_PROJECTUSER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROJECTUSER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROJECTUSER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROJECTUSER):
    case SUCCESS(ACTION_TYPES.UPDATE_PROJECTUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROJECTUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = SERVER_API_URL + '/api/project-users';

// Actions

export const getEntities: ICrudGetAllAction<IProjectUser> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROJECTUSER_LIST,
  payload: axios.get<IProjectUser>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IProjectUser> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROJECTUSER,
    payload: axios.get<IProjectUser>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProjectUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROJECTUSER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProjectUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROJECTUSER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProjectUser> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROJECTUSER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
