import axios from 'axios';
import qs from 'qs';
import { isEmpty } from '@core/utils/is';

axios.defaults.paramsSerializer = (params: any) => {
  for (const key of Object.keys(params)) {
    if (isEmpty(params[key])) {
      delete params[key];
    }
  }

  return qs.stringify(params);
};

export const client = axios.create({
  baseURL: '/api',
  // 카드/태그 엑셀 다운로드 시 응답 시간이 30초 이상 소요되는 경우가 있어 timeout 설정값을 올립니다.
  timeout: 35000,
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
  },
});
