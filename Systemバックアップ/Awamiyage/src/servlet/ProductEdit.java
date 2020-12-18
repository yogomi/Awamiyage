package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Categorys;
import bean.Makers;
import bean.Products;
import dao.CategorysDAO;
import dao.MakersDAO;
import dao.ProductsDAO;

/**
 * Servlet implementation class ProductsAll
 */
@WebServlet("/ProductEdit")
public class ProductEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションを取得
		HttpSession session = request.getSession();

		//ログイン状態の確認、
		//ログイン状態でなければ、ログイン画面に移動
		LoginCheck.isLogin(session, request, response);

		//商品取得
		//データベースに接続、読み込み
		ProductsDAO pdao = new ProductsDAO();
		//検索結果を保持するリストのインスタンスを生成する
		Products products_detail = new Products();
		int pd_id = (int)request.getAttribute("pd_id");
		//daoのdetailメソッドを呼び出して、検索結果を取得する
		products_detail = pdao.detail(pd_id);


		//メーカー情報取得
		//データベースに接続、読み込み
		MakersDAO makerdao = new MakersDAO();
		//検索結果を保持するリストのインスタンスを生成する
		List<Makers> makers_list = new ArrayList();
		//Makersdaoのlistメソッドを呼び出して、検索結果を取得する
		makers_list = makerdao.list();

		//カテゴリ情報取得
		CategorysDAO categorydao = new CategorysDAO();
		List<Categorys> categorys_list = new ArrayList();
		categorys_list = categorydao.categorys();

		//サブカテゴリ情報取得
		CategorysDAO subcategorydao = new CategorysDAO();
		List<Categorys> subcategorys_list = new ArrayList();
		subcategorys_list = subcategorydao.subcategorys();

		//市町村情報取得
		CategorysDAO citydao = new CategorysDAO();
		List<Categorys> citys_list = new ArrayList();
		citys_list = citydao.citys();


		//リクエストにBeanを加える
		request.setAttribute("products_detail", products_detail);
		request.setAttribute("makers_list", makers_list);
		request.setAttribute("categorys_list", categorys_list);
		request.setAttribute("subcategorys_list", subcategorys_list);
		request.setAttribute("citys_list", citys_list);

		//フォワード
		request.getRequestDispatcher("WEB-INF/jsp/back/product_edit.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
