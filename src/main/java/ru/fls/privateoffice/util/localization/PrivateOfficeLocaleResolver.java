package ru.fls.privateoffice.util.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import ru.fls.privateoffice.daoservice.LangService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class PrivateOfficeLocaleResolver extends CookieLocaleResolver{
	
	@Autowired
	private LangService langService;

    private boolean isAdminPage(StringBuffer url){
        if(url.indexOf("/admin/")!=-1 || url.indexOf("/admin_main")!=-1 || url.indexOf("/adminlogin")!=-1 || url.indexOf("/admlogin")!=-1){
            return true;
        }
        return false;
    }
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
        if(isAdminPage(request.getRequestURL())){
            return new Locale("ru");
        }
		Locale locale=super.resolveLocale(request);
		String secretLang=request.getParameter(LangService.SECRET_LANG_PARAMETER);
		if(secretLang!=null){
			String lang=langService.getAvailableLang(secretLang);
			return new Locale(lang);
		}else{
			String lang=langService.getLang(locale.getLanguage());
			return new Locale(lang);
		}
	}
	
	@Override
	public void setLocale(HttpServletRequest request,
                HttpServletResponse response, Locale locale) {
		super.setLocale(request, response, locale);
	}

}
