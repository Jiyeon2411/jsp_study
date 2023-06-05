package ch08;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rcontroller")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RegistService service; //model   
	
    public RegistController() {
        super();
    }
    
    //프로그램 실행 후 최초로 request 들어왔을때 딱 한번만 실행이 된다.
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config); //서블릿 코드
    	
    	//따라서 객체도 프로그램 실행시 딱 한번만 생성이 된다.
    	service = new RegistService(); 
    }

    //get, post방식으로 오는 모든 request 데이터를 처리할 수 있다.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); //action파라메터의 값을 얻어온다.
		String view = "";
		
		//1. 주소에 따라 어떤 페이지를 보여줄지 지정 
		if(action == null) {
			//forward 실행시 페이지는 이동하지만 주소는 바뀌지 않는다.
			//forward 실행시 request, response 데이터를다시 전달해준다.
			getServletContext().getRequestDispatcher("/rcontroller?action=list").forward(request, response);
		} else {
			switch (action) {
				case "list": view = list(request, response); break;
				case "info": view = info(request, response); break;
			}
			
			getServletContext().getRequestDispatcher("/ch08/" + view).forward(request, response);
		}
		
		//2. 페이지에 맞는 데이터를 view페이지로 전달해주면 된다.(포워딩)
	}
	
	//model인 RegistService.java에게 데이터 달라고 요청(메소드 실행)
	private String info(HttpServletRequest request, HttpServletResponse response) {
		Regist prod = service.find(request.getParameter("id"));
		
		request.setAttribute("p", prod);
		return "registinfo.jsp";
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Regist> plist = service.findAll();
		request.setAttribute("register", plist);
		return "regist.list.jsp";
	}

	

}
