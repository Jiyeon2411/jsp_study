package ch09;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/registcontrol")
public class registController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegistDAO dao;
       
    public registController() {
        super();
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		dao = new RegistDAO();
	}
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String view = "";
		
		if(action == null) {
			getServletContext().getRequestDispatcher("/registcontrol?action=list").forward(request, response);
		} else {
			switch (action) {
			case "list" : view = list(request, response); break;
			case "insert": view = insert(request, response); break;
			}
			
			getServletContext().getRequestDispatcher("/ch09/" + view).forward(request, response);
			
			}
			
		}

	private String insert(HttpServletRequest request, HttpServletResponse response) {
		Regist p = new Regist();
		
		try {
			BeanUtils.populate(p, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		dao.insert(p);
		return "registInfo.jsp";
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("register", dao.getAll());
		return "registInfo.jsp";
	}
	

	
	}
