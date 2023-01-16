/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.69
 * Generated at: 2023-01-12 02:33:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.app.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loginview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!doctype html>\n");
      out.write("<html lang=\"ko\">\n");
      out.write("  <head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("    <title>Plan.It</title>\n");
      out.write("    <style>\n");
      out.write("	@import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,800,900');\n");
      out.write("\n");
      out.write("body{\n");
      out.write("  font-family: 'Poppins', sans-serif;\n");
      out.write("  font-weight: 300;\n");
      out.write("  font-size: 15px;\n");
      out.write("  line-height: 1.7;\n");
      out.write("  color: #c4c3ca;\n");
      out.write("  background-color: #1f2029;\n");
      out.write("  overflow-x: hidden;\n");
      out.write("}\n");
      out.write("a {\n");
      out.write("  cursor: pointer;\n");
      out.write("  transition: all 200ms linear;\n");
      out.write("}\n");
      out.write("a:hover {\n");
      out.write("  text-decoration: none;\n");
      out.write("}\n");
      out.write(".link {\n");
      out.write("  color: #c4c3ca;\n");
      out.write("}\n");
      out.write(".link:hover {\n");
      out.write("  color: #ffeba7;\n");
      out.write("}\n");
      out.write("p {\n");
      out.write("  font-weight: 500;\n");
      out.write("  font-size: 14px;\n");
      out.write("  line-height: 1.7;\n");
      out.write("}\n");
      out.write(".login_title {\n");
      out.write("	width : 100%;\n");
      out.write("  font-weight: 600;\n");
      out.write("  font-size: 22px;\n");
      out.write("  text-align : center;\n");
      out.write("  line-height: 30px;\n");
      out.write("}\n");
      out.write("h6 span{\n");
      out.write("  padding: 0 20px;\n");
      out.write("  text-transform: uppercase;\n");
      out.write("  font-weight: 700;\n");
      out.write("}\n");
      out.write(".section{\n");
      out.write("  position: relative;\n");
      out.write("  width: 90%;\n");
      out.write("  padding : 0 2%;\n");
      out.write("  margin : 0 10px;\n");
      out.write("  display: block;\n");
      out.write("}\n");
      out.write(".full-height{\n");
      out.write("  min-height: 100vh;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write(".card-3d-wrap {\n");
      out.write("  position: relative;\n");
      out.write("  width: 100%;\n");
      out.write("  height: 400px;\n");
      out.write("  -webkit-transform-style: preserve-3d;\n");
      out.write("  transform-style: preserve-3d;\n");
      out.write("  perspective: 800px;\n");
      out.write("  margin-top: 60px;\n");
      out.write("  margin-left: 0;\n");
      out.write("}\n");
      out.write(".card-3d-wrapper {\n");
      out.write("  width: 40%;\n");
      out.write("  height: 100%;\n");
      out.write("  margin : 10% auto;\n");
      out.write("  -webkit-transform-style: preserve-3d;\n");
      out.write("  transform-style: preserve-3d;\n");
      out.write("  transition: all 600ms ease-out; \n");
      out.write("}\n");
      out.write(".card-front, .card-back {\n");
      out.write("  width: 100%;\n");
      out.write("  height: 100%;\n");
      out.write("  background-color: #2a2b38;\n");
      out.write("  background-image: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/1462889/pat.svg');\n");
      out.write("  background-position: bottom center;\n");
      out.write("  background-repeat: no-repeat;\n");
      out.write("  background-size: 300%;\n");
      out.write("  position: absolute;\n");
      out.write("  border-radius: 6px;\n");
      out.write("  left: 0;\n");
      out.write("  top: 0;\n");
      out.write("  -webkit-transform-style: preserve-3d;\n");
      out.write("  transform-style: preserve-3d;\n");
      out.write("  -webkit-backface-visibility: hidden;\n");
      out.write("  -moz-backface-visibility: hidden;\n");
      out.write("  -o-backface-visibility: hidden;\n");
      out.write("  backface-visibility: hidden;\n");
      out.write("}\n");
      out.write(".card-back {\n");
      out.write("  transform: rotateY(180deg);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".center-wrap{\n");
      out.write("  position: absolute;\n");
      out.write("  width: 100%;\n");
      out.write("  padding: 0;\n");
      out.write("  top: 47%;\n");
      out.write("  left: 0;\n");
      out.write("  transform: translate3d(0, -50%, 35px) perspective(100px);\n");
      out.write("  z-index: 20;\n");
      out.write("  display: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write(".form-group{ \n");
      out.write("  position: relative;\n");
      out.write("  width:100%;\n");
      out.write("  display: block;\n");
      out.write("   margin: 0 auto;\n");
      out.write("    padding: 0 3%;\n");
      out.write("    \n");
      out.write("  margin-bottom : 10px;\n");
      out.write("}\n");
      out.write(".form-style {\n");
      out.write("  padding: 13px 20px;\n");
      out.write("  padding-left: 30px;\n");
      out.write("  height: 38px;\n");
      out.write("  width: 85%;\n");
      out.write("  font-weight: 500;\n");
      out.write("  border-radius: 4px;\n");
      out.write("  font-size: 14px;\n");
      out.write("  line-height: 22px;\n");
      out.write("  letter-spacing: 0.5px;\n");
      out.write("  outline: none;\n");
      out.write("  color: #c4c3ca;\n");
      out.write("  background-color: #1f2029;\n");
      out.write("  border: none;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("  transition: all 200ms linear;\n");
      out.write("  box-shadow: 0 4px 8px 0 rgba(21,21,21,.2);\n");
      out.write("}\n");
      out.write(".form-style:focus,\n");
      out.write(".form-style:active {\n");
      out.write("  border: none;\n");
      out.write("  outline: none;\n");
      out.write("  box-shadow: 0 4px 8px 0 rgba(21,21,21,.2);\n");
      out.write("}\n");
      out.write(".input-icon {\n");
      out.write("  position: absolute;\n");
      out.write("  top: 0;\n");
      out.write("  left: 18px;\n");
      out.write("  height: 48px;\n");
      out.write("  font-size: 24px;\n");
      out.write("  line-height: 48px;\n");
      out.write("  text-align: left;\n");
      out.write("  color: #ffeba7;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".form-group input:-ms-input-placeholder  {\n");
      out.write("  color: #c4c3ca;\n");
      out.write("  opacity: 0.7;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input::-moz-placeholder  {\n");
      out.write("  color: #c4c3ca;\n");
      out.write("  opacity: 0.7;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input:-moz-placeholder  {\n");
      out.write("  color: #c4c3ca;\n");
      out.write("  opacity: 0.7;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input::-webkit-input-placeholder  {\n");
      out.write("  color: #c4c3ca;\n");
      out.write("  opacity: 0.7;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input:focus:-ms-input-placeholder  {\n");
      out.write("  opacity: 0;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input:focus::-moz-placeholder  {\n");
      out.write("  opacity: 0;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input:focus:-moz-placeholder  {\n");
      out.write("  opacity: 0;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".form-group input:focus::-webkit-input-placeholder  {\n");
      out.write("  opacity: 0;\n");
      out.write("  -webkit-transition: all 200ms linear;\n");
      out.write("    transition: all 200ms linear;\n");
      out.write("}\n");
      out.write(".login_btn_box{\n");
      out.write("	width: 70%;\n");
      out.write("	margin: 0 auto;\n");
      out.write("}\n");
      out.write(".btn{  \n");
      out.write("  margin : 8% auto 2%;\n");
      out.write("  border-radius: 4px;\n");
      out.write("  height: 44px;\n");
      out.write("  line-height : 44px;\n");
      out.write("  width : 49%;\n");
      out.write("  font-size: 13px;\n");
      out.write("  font-weight: 600;\n");
      out.write("  text-transform: uppercase;\n");
      out.write("  padding: 0 30px;\n");
      out.write("  letter-spacing: 1px;\n");
      out.write("  text-align: center;\n");
      out.write("  border: none;\n");
      out.write("  background-color: #ffeba7;\n");
      out.write("  color: #102770;\n");
      out.write("  box-shadow: 0 8px 24px 0 rgba(255,235,167,.2);\n");
      out.write("}\n");
      out.write(".btn:active,\n");
      out.write(".btn:focus{  \n");
      out.write("  background-color: #102770;\n");
      out.write("  color: #ffeba7;\n");
      out.write("  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);\n");
      out.write("}\n");
      out.write(".btn:hover{  \n");
      out.write("  background-color: #102770;\n");
      out.write("  color: #ffeba7;\n");
      out.write("  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);\n");
      out.write("}\n");
      out.write(".login_forgot{\n");
      out.write("	width: 70%;\n");
      out.write("	margin: 0 auto;\n");
      out.write("	text-align: center;\n");
      out.write("}\n");
      out.write("     video { width: 100%;\n");
      out.write("      max-width  : 100%; \n");
      out.write("      height: 100%;\n");
      out.write("      size: cover;\n");
      out.write("      object-fit: fill;\n");
      out.write("		z-index: -999;\n");
      out.write("      position: fixed;\n");
      out.write("      bottom : 0px;\n");
      out.write("      left : 0px;\n");
      out.write("      }\n");
      out.write("      #black_box{\n");
      out.write("  	 width :100%;\n");
      out.write("  	 height : 100%;\n");
      out.write("  	 position: fixed;\n");
      out.write("  	 top: 0px;\n");
      out.write("  	 left: 0px;\n");
      out.write("  	 background-color: rgba(0,0,0,0.7);\n");
      out.write("  	 z-index: -599;\n");
      out.write("  	 }\n");
      out.write("  	 \n");
      out.write("    </style>\n");
      out.write("    	\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("			<video muted autoplay loop>\n");
      out.write("			     <source src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/images/v.mp4\" type=\"video/mp4\" >\n");
      out.write("		         <strong>Your browser does not support the video tag.</strong>\n");
      out.write("			</video>\n");
      out.write("	<div id=\"black_box\"></div>\n");
      out.write("	<div class=\"sections\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <div class=\"row full-height justify-content-center\">\n");
      out.write("        <div class=\"col-12 text-center align-self-center py-5\">\n");
      out.write("          <div class=\"sections pb-5 pt-5 pt-sm-2 text-center\">\n");
      out.write("            <h6 class=\"mb-0 pb-3\"><span>Log In </span><span>Sign Up</span></h6>\n");
      out.write("            <div class=\"card-3d-wrap mx-auto\">\n");
      out.write("              <div class=\"card-3d-wrapper\">\n");
      out.write("                <div class=\"card-front\">\n");
      out.write("                  <div class=\"center-wrap\">\n");
      out.write("                    <div class=\"section text-center\">\n");
      out.write("                      <p class=\"login_title\">Log In</p>\n");
      out.write("	                     <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/user/loginok.tc\" name=\"loginForm\" method=\"post\">\n");
      out.write("	                      <div class=\"form-group\">\n");
      out.write("	                        <input type=\"text\" name=\"userid\" class=\"form-style\" placeholder=\"Your ID\" >\n");
      out.write("	                        <i class=\"input-icon uil uil-at\"></i>\n");
      out.write("	                      </div>  \n");
      out.write("	                      <div class=\"form-group mt-2\">\n");
      out.write("	                        <input type=\"password\" name=\"userpw\" class=\"form-style\" placeholder=\"Your Password\" id=\"logpass\" autocomplete=\"off\">\n");
      out.write("	                        <i class=\"input-icon uil uil-lock-alt\"></i>\n");
      out.write("	                      </div>\n");
      out.write("	                      <!-- <a href=\"#\" class=\"btn mt-4\">submit</a> -->\n");
      out.write("	                      <div class=\"login_btn_box\">\n");
      out.write("	                      	<input type=\"submit\" value=\"Login\" class=\"btn\">\n");
      out.write("	                      	<input type=\"button\" value=\"Join\" class=\"btn\" onclick=\"javascript : location.href='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/user/joinuser.us.tc'\">\n");
      out.write("	                      </div>\n");
      out.write("	                     </form>\n");
      out.write("                                    <p class=\"login_forgot\"><a href=\"#0\" class=\"link\">Forgot your password?</a></p>\n");
      out.write("                        </div>\n");
      out.write("                      </div>\n");
      out.write("                    </div>\n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("      </div>\n");
      out.write("  </div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
