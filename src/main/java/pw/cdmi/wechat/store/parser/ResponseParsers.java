/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package pw.cdmi.wechat.store.parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import pw.cdmi.core.http.client.ResponseMessage;
import pw.cdmi.core.http.client.parser.ResponseParseException;
import pw.cdmi.core.http.client.parser.ResponseParser;
import pw.cdmi.wechat.store.model.Store;

/*
 * A collection of parsers that parse HTTP reponses into corresponding human-readable results.
 */
public final class ResponseParsers {

	public static final ListStoreResponseParser listStoreResponseParser = new ListStoreResponseParser();
	public static final GetStoreResponseParser getStoreResponseParser = new GetStoreResponseParser();
	public static final GetStoreCategoriesResponseParser getStoreCategoriesResponseParser = new GetStoreCategoriesResponseParser();


	public static final class ListStoreResponseParser implements ResponseParser<List<Store>> {

		@Override
		public List<Store> parse(ResponseMessage response) throws ResponseParseException {
			List<Store> list = new ArrayList<Store>();
			JsonReader reader;
			try {
				reader = new JsonReader(new InputStreamReader(response.getContent(), "UTF-8"));
				try {
					reader.beginObject();
					while (reader.hasNext()) {
						String name = reader.nextName();
						if ("business_list".equals(name) && reader.peek() != JsonToken.NULL) {
							reader.beginArray();
							while (reader.hasNext()) {
								reader.beginObject();
								name = reader.nextName();
								if ("base_info".equals(name) && reader.peek() != JsonToken.NULL) {
									Store store = new Store();
									reader.beginObject();
									while (reader.hasNext()) {
										name = reader.nextName();
										if ("sid".equals(name)) {
											store.setSid(reader.nextString());
										} else if ("business_name".equals(name)) {
											store.setBusiness_name(reader.nextString());
										} else if ("branch_name".equals(name)) {
											store.setBranch_name(reader.nextString());
										} else if ("address".equals(name)) {
											store.setAddress(reader.nextString());
										} else if ("telephone".equals(name)) {
											store.setTelephone(reader.nextString());
										} else if ("categories".equals(name)) {
											reader.beginArray();
											List<String> categories = new ArrayList<String>();
											while (reader.hasNext()) {
												categories.add(reader.nextString());
											}
											reader.endArray();
											store.setCategories(categories);
										} else if ("city".equals(name)) {
											store.setCity(reader.nextString());
										} else if ("province".equals(name)) {
											store.setProvince(reader.nextString());
										} else if ("offset_type".equals(name)) {
											store.setOffset_type(reader.nextInt());
										} else if ("longitude".equals(name)) {
											store.setLongitude(reader.nextDouble());
										} else if ("latitude".equals(name)) {
											store.setLatitude(reader.nextDouble());
										} else if ("photo_list".equals(name)) {
											reader.beginArray();
											List<String> photolist = new ArrayList<String>();
											while (reader.hasNext()) {
												photolist.add(reader.nextString());
											}
											reader.endArray();
											store.setPhoto_list(photolist);
										} else if ("introduction".equals(name)) {
											store.setIntroduction(reader.nextString());
										} else if ("recommend".equals(name)) {
											store.setRecommend(reader.nextString());
										} else if ("special".equals(name)) {
											store.setSpecial(reader.nextString());
										} else if ("open_time".equals(name)) {
											store.setOpen_time(reader.nextString());
										} else if ("avg_price".equals(name)) {
											store.setAvg_price(reader.nextInt());
										} else if ("poi_id".equals(name)) {
											store.setPoi_id(reader.nextString());
										} else if ("available_state".equals(name)) {
											store.setAvg_price(reader.nextInt());
										} else if ("district".equals(name)) {
											store.setDistrict(reader.nextString());
										} else if ("update_status".equals(name)) {
											store.setUpdate_status(reader.nextInt());
										} else {
											reader.skipValue();
										}
									}
									reader.endObject();
									list.add(store);
								}
								reader.endObject();
							}
							reader.endArray();
						} else {
							reader.skipValue();
						}
					}
					reader.endObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			return list;
		}
	}

	public static final class GetStoreResponseParser implements ResponseParser<Store> {

		@Override
		public Store parse(ResponseMessage response) throws ResponseParseException {
			Store store = null;
			JsonReader reader;
			try {
				reader = new JsonReader(new InputStreamReader(response.getContent(), "UTF-8"));
				try {
					reader.beginObject();
					while (reader.hasNext()) {
						String name = reader.nextName();
						if ("business".equals(name) && reader.peek() != JsonToken.NULL) {
							reader.beginObject();
							name = reader.nextName();
							if ("base_info".equals(name) && reader.peek() != JsonToken.NULL) {
								store = new Store();
								reader.beginObject();
								while (reader.hasNext()) {
									name = reader.nextName();
									if ("sid".equals(name)) {
										store.setSid(reader.nextString());
									} else if ("business_name".equals(name)) {
										store.setBusiness_name(reader.nextString());
									} else if ("branch_name".equals(name)) {
										store.setBranch_name(reader.nextString());
									} else if ("address".equals(name)) {
										store.setAddress(reader.nextString());
									} else if ("telephone".equals(name)) {
										store.setTelephone(reader.nextString());
									} else if ("categories".equals(name)) {
										reader.beginArray();
										List<String> categories = new ArrayList<String>();
										while (reader.hasNext()) {
											categories.add(reader.nextString());
										}
										reader.endArray();
										store.setCategories(categories);
									} else if ("city".equals(name)) {
										store.setCity(reader.nextString());
									} else if ("province".equals(name)) {
										store.setProvince(reader.nextString());
									} else if ("offset_type".equals(name)) {
										store.setOffset_type(reader.nextInt());
									} else if ("longitude".equals(name)) {
										store.setLongitude(reader.nextDouble());
									} else if ("latitude".equals(name)) {
										store.setLatitude(reader.nextDouble());
									} else if ("photo_list".equals(name)) {
										reader.beginArray();
										List<String> photolist = new ArrayList<String>();
										while (reader.hasNext()) {
											photolist.add(reader.nextString());
										}
										reader.endArray();
										store.setPhoto_list(photolist);
									} else if ("introduction".equals(name)) {
										store.setIntroduction(reader.nextString());
									} else if ("recommend".equals(name)) {
										store.setRecommend(reader.nextString());
									} else if ("special".equals(name)) {
										store.setSpecial(reader.nextString());
									} else if ("open_time".equals(name)) {
										store.setOpen_time(reader.nextString());
									} else if ("avg_price".equals(name)) {
										store.setAvg_price(reader.nextInt());
									} else if ("available_state".equals(name)) {
										store.setAvg_price(reader.nextInt());
									} else if ("district".equals(name)) {
										store.setDistrict(reader.nextString());
									} else if ("update_status".equals(name)) {
										store.setUpdate_status(reader.nextInt());
									} else {
										reader.skipValue();
									}
								}
								reader.endObject();
							}
							reader.endObject();
						} else {
							reader.skipValue();
						}
					}
					reader.endObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			return store;
		}
	}

	public static final class GetStoreCategoriesResponseParser implements ResponseParser<List<String>> {

		@Override
		public List<String> parse(ResponseMessage response) throws ResponseParseException {
			List<String> categories = null;
			JsonReader reader;
			try {
				reader = new JsonReader(new InputStreamReader(response.getContent(), "UTF-8"));
				try {
					reader.beginObject();
					while (reader.hasNext()) {
						String name = reader.nextName();
						if ("category_list".equals(name) && reader.peek() != JsonToken.NULL) {
							reader.beginArray();
							categories = new ArrayList<String>();
							while (reader.hasNext()) {
								categories.add(reader.nextString());
							}
							reader.endArray();
						}
					}
					reader.endObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
			return categories;
		}
	}
}
