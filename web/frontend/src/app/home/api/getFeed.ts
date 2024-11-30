import axios from 'axios';
import { GetFeedResponse } from '../../../../../shared/feed/types';

const API_URL = 'http://localhost:8082/api/feed';

export const getFeed = async (): Promise<GetFeedResponse> => {
  const getFeedResponse = await axios.get<GetFeedResponse>(API_URL);
  return getFeedResponse.data;
};