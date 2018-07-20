import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IPropertyUser, defaultValue } from 'app/shared/model/property-user.model';

export const ACTION_TYPES = {
  FETCH_PROPERTYUSER_LIST: 'propertyUser/FETCH_PROPERTYUSER_LIST',
  FETCH_PROPERTYUSER: 'propertyUser/FETCH_PROPERTYUSER',
  CREATE_PROPERTYUSER: 'propertyUser/CREATE_PROPERTYUSER',
  UPDATE_PROPERTYUSER: 'propertyUser/UPDATE_PROPERTYUSER',
  DELETE_PROPERTYUSER: 'propertyUser/DELETE_PROPERTYUSER',
  RESET: 'propertyUser/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPropertyUser>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PropertyUserState = Readonly<typeof initialState>;

// Reducer

export default (state: PropertyUserState = initialState, action): PropertyUserState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROPERTYUSER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROPERTYUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROPERTYUSER):
    case REQUEST(ACTION_TYPES.UPDATE_PROPERTYUSER):
    case REQUEST(ACTION_TYPES.DELETE_PROPERTYUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROPERTYUSER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROPERTYUSER):
    case FAILURE(ACTION_TYPES.CREATE_PROPERTYUSER):
    case FAILURE(ACTION_TYPES.UPDATE_PROPERTYUSER):
    case FAILURE(ACTION_TYPES.DELETE_PROPERTYUSER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPERTYUSER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPERTYUSER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROPERTYUSER):
    case SUCCESS(ACTION_TYPES.UPDATE_PROPERTYUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROPERTYUSER):
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

const apiUrl = SERVER_API_URL + '/api/property-users';

// Actions

export const getEntities: ICrudGetAllAction<IPropertyUser> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROPERTYUSER_LIST,
  payload: axios.get<IPropertyUser>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPropertyUser> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROPERTYUSER,
    payload: axios.get<IPropertyUser>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPropertyUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROPERTYUSER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPropertyUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROPERTYUSER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPropertyUser> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROPERTYUSER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
