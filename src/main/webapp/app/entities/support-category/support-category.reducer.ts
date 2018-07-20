import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { ISupportCategory, defaultValue } from 'app/shared/model/support-category.model';

export const ACTION_TYPES = {
  FETCH_SUPPORTCATEGORY_LIST: 'supportCategory/FETCH_SUPPORTCATEGORY_LIST',
  FETCH_SUPPORTCATEGORY: 'supportCategory/FETCH_SUPPORTCATEGORY',
  CREATE_SUPPORTCATEGORY: 'supportCategory/CREATE_SUPPORTCATEGORY',
  UPDATE_SUPPORTCATEGORY: 'supportCategory/UPDATE_SUPPORTCATEGORY',
  DELETE_SUPPORTCATEGORY: 'supportCategory/DELETE_SUPPORTCATEGORY',
  RESET: 'supportCategory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISupportCategory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SupportCategoryState = Readonly<typeof initialState>;

// Reducer

export default (state: SupportCategoryState = initialState, action): SupportCategoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SUPPORTCATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SUPPORTCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SUPPORTCATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_SUPPORTCATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_SUPPORTCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SUPPORTCATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SUPPORTCATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_SUPPORTCATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_SUPPORTCATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_SUPPORTCATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUPPORTCATEGORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SUPPORTCATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SUPPORTCATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_SUPPORTCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SUPPORTCATEGORY):
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

const apiUrl = SERVER_API_URL + '/api/support-categories';

// Actions

export const getEntities: ICrudGetAllAction<ISupportCategory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SUPPORTCATEGORY_LIST,
  payload: axios.get<ISupportCategory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISupportCategory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SUPPORTCATEGORY,
    payload: axios.get<ISupportCategory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISupportCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SUPPORTCATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISupportCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SUPPORTCATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISupportCategory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SUPPORTCATEGORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
