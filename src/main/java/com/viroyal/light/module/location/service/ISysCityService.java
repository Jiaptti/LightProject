package com.viroyal.light.module.location.service;

import com.viroyal.light.module.location.entity.SysCity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaptti
 * @since 2018-01-04
 */
public interface ISysCityService extends IService<SysCity> {
	/**
	 * 查询城市
	 */
	List<SysCity> queryAllCity();

	/**
	 * 添加城市
	 * @param city  城市
	 */
	void saveCity(SysCity city);

	/**
	 * 删除城市
	 * @param ids  城市id数组
	 */
	int deleteCityBatch(Object[] ids);


	/**
	 * 更新城市城市
	 * @param city  城市id数组
	 */
	void updateCity(SysCity city);
}
