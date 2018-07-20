import isEqual from 'lodash/isEqual';
import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IProperty, defaultValue } from 'app/shared/model/property.model';

export const ACTION_TYPES = {
  FETCH_PROPERTY_LIST: 'property/FETCH_PROPERTY_LIST',
  FETCH_PROPERTY: 'property/FETCH_PROPERTY',
  CREATE_PROPERTY: 'property/CREATE_PROPERTY',
  UPDATE_PROPERTY: 'property/UPDATE_PROPERTY',
  DELETE_PROPERTY: 'property/DELETE_PROPERTY',
  SET_BLOB: 'property/SET_BLOB',
  RESET: 'property/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProperty>,
  entity: defaultValue,
  links: {
    last: 0
  },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PropertyState = Readonly<typeof initialState>;

// Reducer

export default (state: PropertyState = initialState, action): PropertyState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROPERTY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROPERTY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROPERTY):
    case REQUEST(ACTION_TYPES.UPDATE_PROPERTY):
    case REQUEST(ACTION_TYPES.DELETE_PROPERTY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROPERTY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROPERTY):
    case FAILURE(ACTION_TYPES.CREATE_PROPERTY):
    case FAILURE(ACTION_TYPES.UPDATE_PROPERTY):
    case FAILURE(ACTION_TYPES.DELETE_PROPERTY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPERTY_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links: { last: links.last },
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPERTY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROPERTY):
    case SUCCESS(ACTION_TYPES.UPDATE_PROPERTY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROPERTY):
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

const apiUrl = SERVER_API_URL + '/api/properties';

// Actions

export const getEntities: ICrudGetAllAction<IProperty> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROPERTY_LIST,
    payload: axios.get<IProperty>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IProperty> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROPERTY,
    payload: axios.get<IProperty>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProperty> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROPERTY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProperty> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROPERTY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProperty> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROPERTY,
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
