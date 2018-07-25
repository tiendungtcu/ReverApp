import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IExchanger, defaultValue } from 'app/shared/model/exchanger.model';

export const ACTION_TYPES = {
  FETCH_EXCHANGER_LIST: 'exchanger/FETCH_EXCHANGER_LIST',
  FETCH_EXCHANGER: 'exchanger/FETCH_EXCHANGER',
  CREATE_EXCHANGER: 'exchanger/CREATE_EXCHANGER',
  UPDATE_EXCHANGER: 'exchanger/UPDATE_EXCHANGER',
  DELETE_EXCHANGER: 'exchanger/DELETE_EXCHANGER',
  RESET: 'exchanger/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IExchanger>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ExchangerState = Readonly<typeof initialState>;

// Reducer

export default (state: ExchangerState = initialState, action): ExchangerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EXCHANGER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EXCHANGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EXCHANGER):
    case REQUEST(ACTION_TYPES.UPDATE_EXCHANGER):
    case REQUEST(ACTION_TYPES.DELETE_EXCHANGER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EXCHANGER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EXCHANGER):
    case FAILURE(ACTION_TYPES.CREATE_EXCHANGER):
    case FAILURE(ACTION_TYPES.UPDATE_EXCHANGER):
    case FAILURE(ACTION_TYPES.DELETE_EXCHANGER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXCHANGER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EXCHANGER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EXCHANGER):
    case SUCCESS(ACTION_TYPES.UPDATE_EXCHANGER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EXCHANGER):
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

const apiUrl = SERVER_API_URL + '/api/exchangers';

// Actions

export const getEntities: ICrudGetAllAction<IExchanger> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EXCHANGER_LIST,
  payload: axios.get<IExchanger>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IExchanger> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EXCHANGER,
    payload: axios.get<IExchanger>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IExchanger> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EXCHANGER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IExchanger> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EXCHANGER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IExchanger> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EXCHANGER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
