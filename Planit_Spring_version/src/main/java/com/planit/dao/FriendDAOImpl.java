package com.planit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.planit.domain.FriendDTO;
import com.planit.domain.GroupDTO;
import com.planit.domain.UserDTO;

import lombok.Setter;

@Repository
public class FriendDAOImpl implements FriendDAO {
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlSession;

	private static String namespace = "com.planit.mapper.FriendMapper.";

	@Override
	public ArrayList<UserDTO> getFriendList(String userid) {

		List<FriendDTO> flist = null;
		flist = sqlSession.selectList(namespace + "getFriendList", userid);

		ArrayList<UserDTO> idlist = new ArrayList<UserDTO>();

		for (int i = 0; i < flist.size(); i++) {
			UserDTO udto = new UserDTO();
			if (flist.get(i).getUserid().equals(userid)) {
				udto.setUserid(flist.get(i).getFriendid());
				udto.setUsername(flist.get(i).getFriendname());
			} else {
				udto.setUserid(flist.get(i).getUserid());
				udto.setUsername(flist.get(i).getUsername());
			}
			idlist.add(udto);

		}

		return idlist;
	}

	@Override
	public HashMap<String, UserDTO> getFriendMap(String userid) {
		List<FriendDTO> flist = new ArrayList<FriendDTO>();
		flist = sqlSession.selectList(namespace + "getFriendList", userid);
		HashMap<String, UserDTO> idlist = new HashMap<String, UserDTO>();

		for (int i = 0; i < flist.size(); i++) {
			UserDTO udto = new UserDTO();
			if (flist.get(i).getUserid().equals(userid)) {
				udto.setUserid(flist.get(i).getFriendid());
				udto.setUsername(flist.get(i).getFriendname());
			} else {
				udto.setUserid(flist.get(i).getUserid());
				udto.setUsername(flist.get(i).getUsername());
			}
			idlist.put(udto.getUserid(), udto);
			// System.out.println(idlist.get(i).getUserid());

		}

		return idlist;
	}

	@Override
	public List<GroupDTO> getFriendNAutho(int scnum) {
		return sqlSession.selectList(namespace + "getFriendNAutho", scnum);
	}

	@Override
	public List<String> getFriendName(int scnum) {
		return sqlSession.selectList(namespace + "getFriendName", scnum);
	}

	@Override
	public boolean friendOk(String userid) {
		return (Integer) sqlSession.update(namespace + "friendOk", userid) == 1;
	}

	@Override
	public boolean friendNo(String userid) {
		return (Integer) sqlSession.delete(namespace + "friendNo", userid) == 1;
	}

	@Override
	public boolean friendblock(String friendid, String userid) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("friendid", friendid);
		return (Integer) sqlSession.update(namespace + "friendBlock", datas) == 1;
	}

	@Override
	public boolean friendblockend(String userid) {
		return (Integer) sqlSession.update(namespace + "friendBlockEnd", userid) == 1;
	}

	@Override
	public List<FriendDTO> getconfirmList(String userid) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		List<FriendDTO> result = null;

		if (userid == null) {
			result = sqlSession.selectList(namespace + "getConfirmList", datas);
		} else {
			datas.put("userid", userid);
			result = sqlSession.selectList(namespace + "getConfirmListWithKey", datas);
		}

		return result;
	}

	@Override
	public List<FriendDTO> getfriendlsList(String userid) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		List<FriendDTO> result = null;

		if (userid != null) {
			datas.put("userid", userid);
			result = sqlSession.selectList(namespace + "getFriendlsListWithKey", datas);
		}

		return result;
	}

	@Override
	public List<FriendDTO> getfriendBlockList(String userid) {
		System.out.println("userid" + userid);
		HashMap<String, Object> datas = new HashMap<String, Object>();
		List<FriendDTO> result = null;
		if (userid == null) {
			result = sqlSession.selectList(namespace + "getFriendBlockList", datas);
		} else {
			datas.put("userid", userid);
			result = sqlSession.selectList(namespace + "getFriendBlockListWithKey", datas);
		}

		return result;
	}

	@Override
	public boolean add(FriendDTO friend) {
		return sqlSession.insert(namespace + "add", friend) == 1;
	}

	@Override
	public List<FriendDTO> getfrlsList(String userid) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		List<FriendDTO> result = null;

		if (userid == null) {
			result = sqlSession.selectList(namespace + "getFrlsList", datas);
		} else {
			datas.put("userid", userid);
			result = sqlSession.selectList(namespace + "getFrlsListWithKey", datas);
		}
		return result;
	}

}