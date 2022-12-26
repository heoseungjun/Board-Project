package com.campus.myapp.service;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.campus.myapp.dao.DataDAO;
import com.campus.myapp.vo.DataVO;

@Service
public class DataServiceImpl implements DataService {
	@Inject
	DataDAO dao;

	@Override
	public int dataInsert(DataVO vo) {
		return dao.dataInsert(vo);
	}

	@Override
	public List<DataVO> dataSelect(DataVO vo) {
		return dao.dataSelect(vo);
	}

	@Override
	public DataVO dataView(int no) {
		return dao.dataView(no);
	}

	@Override
	public DataVO selectFilename(int no) {
		return dao.selectFilename(no);
	}

	@Override
	public int dataUpdate(DataVO vo) {
		return dao.dataUpdate(vo);
	}

	@Override
	public int dataDelete(int no,String userid) {
		return dao.dataDelete(no,userid);
	}
}
