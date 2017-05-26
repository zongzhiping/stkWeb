package com.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.BcDAO;
import com.dao.DdxtDAO;
import com.dao.DxtDAO;
import com.dao.KsDAO;
import com.dao.KstDAO;
import com.dao.PdDAO;
import com.dao.StuDAO;
import com.model.Bc;
import com.model.Ddxt;
import com.model.Dxt;
import com.model.Ks;
import com.model.Kst;
import com.model.Pd;
import com.model.Stu;
import com.model.StuKs;
import com.model.Tm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class KstAction extends ActionSupport {

	private int id;
	private String[] selectFlag;
	private int type;
	private int tid;
	private int kid;
	private String message;
	private String path;
	private KstDAO dao;
	private KsDAO ksdao;
	private DxtDAO dxtdao;
	private DdxtDAO ddxtdao;
	private PdDAO pddao;
	private BcDAO bcdao;
	private StuDAO studao;

	public String add() {
		Kst demo = new Kst();
		// demo.setName(this.getName());
		demo.setTid(id);
		demo.setKid(kid);
		demo.setType(type);
		demo.setFs(0);
		dao.save(demo);

		// �޸�״̬
		Ks ks = ksdao.findById(kid);
		if (type == 1) {
			ks.setDx(1);
		} else if (type == 2) {
			ks.setDdx(1);
		} else if (type == 3) {
			ks.setPd(1);
		} else if (type == 4) {
			ks.setBc(1);
		}

		ksdao.merge(ks);
		this.setMessage("��ӳɹ�");
		// this.setPath("ok");
		return "succeed";
	}

	/*
	 * ��ɿ���
	 */
	public String wcks() {
		Ks ks = ksdao.findById(id);
		int kstype = ks.getType();
		int fs = dao.wcks(id);
		if (fs != 100) {
			ksdao.delete(ks);
			this.setMessage("����ʧ�ܣ����Է�����Ϊ100�����������");
			this.setPath("admin/ksAdd.jsp?type=" + kstype);
		} else {
			this.setMessage("�����ɹ�");
			this.setPath("ksList.action?type=" + kstype);
		}
		return "succeed";
	}

	public String delete() {

		System.out.println("================  " + selectFlag.length);
		for (int i = 0; i < selectFlag.length; i++) {
			System.out.println("================  " + selectFlag[i]);
			Kst demo = dao.findById(Integer.parseInt(selectFlag[i]));
			dao.delete(demo);
		}
		this.setMessage("�����ɹ�");
		this.setPath("KstList.action");
		return "succeed";
	}

	public String modifybefore() {

		Kst demo = dao.findById(Integer.parseInt(selectFlag[0]));
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("bean", demo);
		return "mb";
	}

	public String modify() {
		Kst demo = dao.findById(id);
		dao.merge(demo);
		this.setMessage("�޸ĳɹ�");
		this.setPath("KstList.action");
		return "succeed";
	}

	public String all() {
		List list = dao.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "list";
	}

	/*
	 * ѧ������ǰ
	 */
	public String stuksb() {
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		int stuid = Integer.parseInt(session.get("id").toString());
		// Integer.parseInt(selectFlag[0])
		List list = dao.findByProperty("kid", Integer.parseInt(selectFlag[0]));
		Ks ks1 = ksdao.findById(Integer.parseInt(selectFlag[0]));
		int kstype = ks1.getType();
		for (int i = 0; i < list.size(); i++) {
			Tm tm = new Tm();
			Kst bean = (Kst) list.get(i);
			int tid = bean.getTid();
			int typ = bean.getType();
			tm.setFs(bean.getFs());
			tm.setKid(bean.getKid());

			tm.setStuid(stuid);
			tm.setTid(tid);
			tm.setType(typ);
			if (typ == 1) {
				Dxt t = dxtdao.findById(tid);

				if (kstype == 1) {
					Random r = new Random();
					int temp1 = r.nextInt();
					if (temp1 % 2 == 0) {
						tm.setA(t.getB());
						tm.setB(t.getD());
						tm.setC(t.getA());
						tm.setD(t.getC());
						if (t.getDa().equals("A")) {
							tm.setDa("C");
						} else if (t.getDa().equals("B")) {
							tm.setDa("A");
						} else if (t.getDa().equals("C")) {
							tm.setDa("D");
						} else if (t.getDa().equals("D")) {
							tm.setDa("B");
						}

					} else {
						tm.setA(t.getC());
						tm.setB(t.getA());
						tm.setC(t.getD());
						tm.setD(t.getB());
						if (t.getDa().equals("A")) {
							tm.setDa("B");
						} else if (t.getDa().equals("B")) {
							tm.setDa("D");
						} else if (t.getDa().equals("C")) {
							tm.setDa("A");
						} else if (t.getDa().equals("D")) {
							tm.setDa("C");
						}
					}
				} else {
					tm.setA(t.getA());
					tm.setB(t.getB());
					tm.setC(t.getC());
					tm.setD(t.getD());
					tm.setDa(t.getDa());
				}
				tm.setTitle(t.getTitle());
			} else if (typ == 2) {
				Ddxt t = ddxtdao.findById(tid);
				tm.setA(t.getA());
				tm.setB(t.getB());
				tm.setC(t.getC());
				tm.setD(t.getD());
				tm.setDa(t.getDa());
				tm.setTitle(t.getTitle());
			} else if (typ == 3) {
				Pd t = pddao.findById(tid);
				tm.setDa(t.getDa());
				tm.setTitle(t.getTitle());
			} else if (typ == 4) {
				Bc t = bcdao.findById(tid);
				tm.setTitle(t.getTitle());
				tm.setDa(t.getDa());
			}

			// ����tm
			dao.saveTm(tm);
		}
		List listdxt = dao.findAllTmType(stuid, selectFlag[0], 1);
		List listddxt = dao.findAllTmType(stuid, selectFlag[0], 2);
		List listpd = dao.findAllTmType(stuid, selectFlag[0], 3);
		List listbc = dao.findAllTmType(stuid, selectFlag[0], 4);
		Ks ks = ksdao.findById(Integer.parseInt(selectFlag[0]));
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listdxt", listdxt);
		request.setAttribute("listddxt", listddxt);
		request.setAttribute("listpd", listpd);
		request.setAttribute("listbc", listbc);
		request.setAttribute("bean", ks);
		return "success";

	}
	
	/*
	 * ��ɿ��Ե��
	 */
	public String wcks2() {
		
		List listdxt = dao.findAllType(id, 1);
		List listddxt = dao.findAllType(id, 2);
		List listpd = dao.findAllType(id, 3);
		List listbc = dao.findAllType(id, 4);
		Ks ks = ksdao.findById(id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listdxt", listdxt);
		request.setAttribute("listddxt", listddxt);
		request.setAttribute("listpd", listpd);
		request.setAttribute("listbc", listbc);
		request.setAttribute("bean", ks);
		return "success";

	}

	/*
	 * ȷ������
	 */
	public String qdfs() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("id");
		int totle=0;
		List list = new ArrayList();
		for (int i = 0; i < ids.length; i++) {
			String oneId = ids[i];
			Kst tm = dao.findById(Integer.parseInt(oneId));
			int typ = tm.getType();
			if (typ == 1) {
				String dxtValue = request.getParameter("dxt" + oneId);
				System.out.println("============dxtValue " + dxtValue);
				tm.setFs(Integer.parseInt(dxtValue));
				totle+=Integer.parseInt(dxtValue);
			} else if (typ == 2) {
				String ddxtValue = request.getParameter("ddxt" + oneId);
				System.out.println("============ddxtValue " + ddxtValue);
				tm.setFs(Integer.parseInt(ddxtValue));
				totle+=Integer.parseInt(ddxtValue);
			} else if (typ == 3) {
				String pdValue = request.getParameter("pd" + oneId);
				System.out.println("============pdValue " + pdValue);
				tm.setFs(Integer.parseInt(pdValue));
				totle+=Integer.parseInt(pdValue);
			} else if (typ == 4) {
				String bcValue = request.getParameter("bc" + oneId);
				System.out.println("============bcValue " + bcValue);
				tm.setFs(Integer.parseInt(bcValue));
				totle+=Integer.parseInt(bcValue);
			}
			list.add(tm);
		}
		if(totle==100){//����
			for (int i = 0; i < list.size(); i++) {
				Kst bean = (Kst)list.get(i);
				dao.merge(bean);
			}
			this.setMessage("�ƶ��ɹ�");
			this.setPath("ksList.action?type=2");
		}else{
			this.setMessage("�ֲܷ�Ϊ100�����������");
			this.setPath("wcks2.action?id="+kid+"&type=2");
			
		}
		return "succeed";
	}
	
	
	/*
	 * ����
	 */
	public String jj() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("id");
		String ksid = "";
		for (int i = 0; i < ids.length; i++) {
			String oneId = ids[i];
			Tm tm = dao.findTmById(Integer.parseInt(oneId));
			ksid=tm.getKid()+"";
			String zqda = tm.getDa();
			int typ = tm.getType();
			if (typ == 1) {
				String dxtValue = request.getParameter("dxt" + oneId);
				System.out.println("============dxtValue " + dxtValue);
				if(dxtValue==null){
					tm.setStuda("");
					tm.setStufs(0);
				}else{
					tm.setStuda(dxtValue);
					if(zqda.equals(dxtValue)){
						tm.setStufs(tm.getFs());
					}else{
						tm.setStufs(0);
					}
				}
				
			} else if (typ == 2) {
				String[] ddxtValue = request.getParameterValues("ddxt" + oneId);
				System.out.println("============ddxtValue " + ddxtValue);
				String text = "";
				if(ddxtValue==null){
					tm.setStuda("");
					tm.setStufs(0);
				}else{
					for (int j = 0; j < ddxtValue.length; j++) {
						if (j == 0) {

						} else {
							text += ",";
						}
						text += ddxtValue[j];
					}
					tm.setStuda(text);
					if(zqda.equals(text)){
						tm.setStufs(tm.getFs());
					}else{
						tm.setStufs(0);
					}
				}
				
			} else if (typ == 3) {
				String pdValue = request.getParameter("pd" + oneId);
				System.out.println("============pdValue " + pdValue);
				if(pdValue==null){
					tm.setStuda("");
					tm.setStufs(0);
				}else{
					tm.setStuda(pdValue);
					if(zqda.equals(pdValue)){
						tm.setStufs(tm.getFs());
					}else{
						tm.setStufs(0);
					}
				}
			} else if (typ == 4) {
				String bcValue = request.getParameter("bc" + oneId);
				System.out.println("============bcValue " + bcValue);
				if(bcValue==null){
					tm.setStuda("");
					tm.setStufs(0);
				}else{
					tm.setStuda(bcValue);
					if(zqda.equals(bcValue)){
						tm.setStufs(tm.getFs());
					}else{
						tm.setStufs(0);
					}
				}
			}

			// �޸�
			dao.mergeTm(tm);
		}
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		int stuid = Integer.parseInt(session.get("id").toString());
		// �������֮����ӵ�����ͳ�Ʊ�
		List listStuFs = dao.findFsByStu(ksid,stuid+"");
		Ks ks = ksdao.findById(Integer.parseInt(ksid));// ����
		int stufs=0;
		for (int i = 0; i < listStuFs.size(); i++) {
			Tm sk = (Tm) listStuFs.get(0);
			stufs+= sk.getStufs();
		}
		Stu stu = studao.findById(stuid);// ѧ��
		StuKs stuks = new StuKs();
		//stuks.setBj(stu.getBj());
		stuks.setFs(stufs);
		stuks.setKid(ks.getId());
		stuks.setRealName(stu.getRealName());
		//stuks.setSex(stu.getSex());
		stuks.setTitle(ks.getTitle());
		stuks.setUserNo(stu.getUserNo());
		//stuks.setXy(stu.getXy());
		//stuks.setZy(stu.getZy());
		stuks.setStuid(stuid);
		stuks.setState(1);
		dao.saveStuKs(stuks);
		
		
		List listdxt = dao.findAllTmType(stuid, ksid, 1);
		List listddxt = dao.findAllTmType(stuid, ksid, 2);
		List listpd = dao.findAllTmType(stuid, ksid, 3);
		List listbc = dao.findAllTmType(stuid, ksid, 4);
		request.setAttribute("listdxt", listdxt);
		request.setAttribute("listddxt", listddxt);
		request.setAttribute("listpd", listpd);
		request.setAttribute("listbc", listbc);
		return "success";
		
	}

	/*
	 * �Զ��ľ�
	 */
	public String zdyj() {
		// Integer.parseInt(selectFlag[0])
		List list = dao.findAllTm(selectFlag[0]);
		for (int i = 0; i < list.size(); i++) {
			Tm tm = (Tm) list.get(i);
			int typ = tm.getType();
			if (typ == 1) {
				String da = tm.getDa();
				String studa = tm.getStuda();
				if (da.equals(studa)) {
					tm.setStufs(tm.getFs());
				} else {
					tm.setStufs(0);
				}
			} else if (typ == 2) {
				String da = tm.getDa();
				String studa = tm.getStuda();
				if (da.equals(studa)) {
					tm.setStufs(tm.getFs());
				} else {
					tm.setStufs(0);
				}
			} else if (typ == 3) {
				String da = tm.getDa();
				String studa = tm.getStuda();
				if (da.equals(studa)) {
					tm.setStufs(tm.getFs());
				} else {
					tm.setStufs(0);
				}
			} else if (typ == 4) {
			}
		}
		// �������֮����ӵ�����ͳ�Ʊ�
		List listStuFs = dao.findFsGroupByStu(selectFlag[0]);
		Ks ks = ksdao.findById(Integer.parseInt(selectFlag[0]));// ����
		for (int i = 0; i < listStuFs.size(); i++) {
			StuKs sk = (StuKs) listStuFs.get(0);
			int stufs = sk.getFs();
			int stuid = sk.getKid();
			Stu stu = studao.findById(stuid);// ѧ��
			StuKs stuks = new StuKs();
			//stuks.setBj(stu.getBj());
			stuks.setFs(stufs);
			stuks.setKid(ks.getId());
			stuks.setRealName(stu.getRealName());
			//stuks.setSex(stu.getSex());
			stuks.setTitle(ks.getTitle());
			stuks.setUserNo(stu.getUserNo());
			//stuks.setXy(stu.getXy());
			//stuks.setZy(stu.getZy());
			stuks.setStuid(stuid);
			stuks.setState(1);
			dao.saveStuKs(stuks);

		}
		if (ks.getBc() == 0) {
			ks.setState(2);
			this.setMessage("�ľ�ɹ�,���Ծ�û�б���⣬������ֱ��ȥ�ɼ���ѯ����鿴�ɼ�");
		} else {
			ks.setState(1);
			this.setMessage("�ľ�ɹ�,���Ծ��б���⣬����Ҫ��ȥ�ύ���������ſ��Բ鿴�ɼ�");
		}
		ksdao.merge(ks);
		this.setPath("ksList.action?type=6");
		return "succeed";
	}

	/*
	 * ���Զ��ľ��б�
	 */
	public String bzdyjList() {

		List list = dao.findAllStuKs(selectFlag[0]);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "success";
	}

	/*
	 * ���Զ��ľ�һ��
	 */
	public String bzdyjOne() {

		String[] str = selectFlag[0].split(",");
		List listbc = dao.findAllTmType(Integer.parseInt(str[0]), str[1], 4);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listbc", listbc);
		return "success";
	}

	/*
	 * ���Զ��ľ����
	 */
	public String bzdyjok() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("id");
		int stuid = 0;
		int kid = 0;
		int totleFs = 0;
		for (int i = 0; i < ids.length; i++) {
			String oneId = ids[i];
			Tm tm = dao.findTmById(Integer.parseInt(oneId));
			String fsValue = request.getParameter("fs" + oneId);
			System.out.println("============fsValue " + fsValue);
			tm.setStufs(Integer.parseInt(fsValue));
			totleFs += Integer.parseInt(fsValue);
			stuid = tm.getStuid();
			kid = tm.getKid();
			// �޸�
			dao.mergeTm(tm);
		}

		// ���·�����״̬
		List listOne = dao.findOneStuKs(kid, stuid);
		StuKs one = (StuKs) listOne.get(0);
		one.setFs(one.getFs() + totleFs);
		one.setState(2);
		dao.mergeStuKs(one);
		// ���¿���״̬
		List list = dao.findAllStuKs(kid + "");
		if (list.size() == 0) {// ȫ���ľ����
			Ks ks = ksdao.findById(kid);// ����
			ks.setState(2);
			ksdao.merge(ks);
		}
		this.setPath("ksList.action?type=7");
		this.setMessage("�ľ�ɹ�,����Ϊ:"+one.getFs());
		return "succeed";
	}

	/*
	 * �ɼ���ѯ�б�
	 */
	public String cjcxList() {

		List list = dao.findStuKs(selectFlag[0]);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "success";
	}

	public String szdKstList() {
		List list = dao.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "list";
	}
	
	public String mycjlist() {
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		int stuid = Integer.parseInt(session.get("id").toString());
		List list = dao.findStuKsByStuid(stuid+"");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return "success";
	}
	
	

	public String search() {
		List list = dao.findByProperty("", "");
		Map request = (Map) ServletActionContext.getContext().get("request");
		request.put("list", list);
		return ActionSupport.SUCCESS;
	}

	public KstDAO getDao() {
		return dao;
	}

	public void setDao(KstDAO dao) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public KsDAO getKsdao() {
		return ksdao;
	}

	public void setKsdao(KsDAO ksdao) {
		this.ksdao = ksdao;
	}


	public DxtDAO getDxtdao() {
		return dxtdao;
	}

	public void setDxtdao(DxtDAO dxtdao) {
		this.dxtdao = dxtdao;
	}

	public DdxtDAO getDdxtdao() {
		return ddxtdao;
	}

	public void setDdxtdao(DdxtDAO ddxtdao) {
		this.ddxtdao = ddxtdao;
	}

	public PdDAO getPddao() {
		return pddao;
	}

	public void setPddao(PdDAO pddao) {
		this.pddao = pddao;
	}

	public BcDAO getBcdao() {
		return bcdao;
	}

	public void setBcdao(BcDAO bcdao) {
		this.bcdao = bcdao;
	}

	public StuDAO getStudao() {
		return studao;
	}

	public void setStudao(StuDAO studao) {
		this.studao = studao;
	}
}
