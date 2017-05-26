package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.DdxtDAO;
import com.model.Ddxt;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Pagination;

public class DdxtAction extends ActionSupport {

	private int id;
	private String[] selectFlag;
	private String title;
	private String a;
	private String b;
	private String c;
	private String d;
	private String da;
	private String message;
	private String path;
	private DdxtDAO dao;
	private int index = 1;
	public String add() {
		Ddxt demo = new Ddxt();
		// demo.setName(this.getName());
		demo.setTitle(title);
		demo.setA(a);
		demo.setB(b);
		demo.setC(c);
		demo.setD(d);
		demo.setDa(da);
		dao.save(demo);
		this.setMessage("��ӳɹ�");
		this.setPath("admin/ddxtAdd.jsp");
		return "succeed";
	}

	public String delete() {
		
		System.out.println("================  "+selectFlag.length);
		for (int i = 0; i < selectFlag.length; i++) {
			System.out.println("================  "+selectFlag[i]);
			Ddxt demo = dao.findById(Integer.parseInt(selectFlag[i]));
			dao.delete(demo);
		}
		this.setMessage("�����ɹ�");
		this.setPath("ddxtList.action");
		return "succeed";
	}

	public String modifybefore() {
		
		Ddxt demo = dao.findById(Integer.parseInt(selectFlag[0]));
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("bean", demo);
		return "mb";
	}

	public String modify() {
		Ddxt demo = dao.findById(id);
		demo.setTitle(title);
		demo.setA(a);
		demo.setB(b);
		demo.setC(c);
		demo.setD(d);
		demo.setDa(da);
		dao.merge(demo);
		this.setMessage("�޸ĳɹ�");
		this.setPath("ddxtList.action");
		return "succeed";
	}

	public String all() {
		List list = new ArrayList();
		list = dao.findAll();

		int pageSize = 10;
		int fromIndex = (index - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, list.size());
		List adminListFenye = list.subList(fromIndex, toIndex);

		Pagination p = new Pagination();// ���� ��ҳ����
		p.setIndex(index);// ����ҳ��
		p.setPageSize(pageSize);
		p.setTotle(list.size());// �����ܹ�������
		p.setData(adminListFenye);// ��������
		p.setPath("stuList.action");// ��ת��·��

		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("page", p);
		return "list";
	}
	
	public String szdddxtList() {
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		List list = dao.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "list";
	}

	public String search() {
		List list = dao.findByProperty("", "");
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("list", list);
		return ActionSupport.SUCCESS;
	}

	public DdxtDAO getDao() {
		return dao;
	}

	public void setDao(DdxtDAO dao) {
		this.dao = dao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}
	
	public String[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}
}
