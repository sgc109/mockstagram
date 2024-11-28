import axios from 'axios';
import { GetFeedResponse } from '../../../../../shared/feed/types';

const API_URL = 'http://localhost:8080/api/feed';

export const getFeed = async (): Promise<GetFeedResponse> => {
  const getFeedResponse = await axios.get<GetFeedResponse>(API_URL);
  return getFeedResponse.data;
};