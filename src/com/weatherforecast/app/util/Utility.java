package com.weatherforecast.app.util;

import android.text.TextUtils;

import com.weatherforecast.app.db.WeatherForecastDB;
import com.weatherforecast.app.model.City;
import com.weatherforecast.app.model.County;
import com.weatherforecast.app.model.Province;

public class Utility {
	/**
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvincesResponse(
			WeatherForecastDB weatherFroecastDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// �������������ݴ洢��Province����
					weatherFroecastDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ʹ�����������ص�City������
	 */
	public synchronized static boolean handleCitiesResponse(
			WeatherForecastDB weatherFroecastDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					// �������������ݴ洢��City����
					weatherFroecastDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ʹ�����������ص�County������
	 */
	public synchronized static boolean handleCountiesResponse(
			WeatherForecastDB weatherFroecastDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					// �������������ݴ洢��City����
					weatherFroecastDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
