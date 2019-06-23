package com.showTime.common.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class FileDownload {

	/**
	 * @param request
	 * @param response
	 * @param fileAbsPath 文件绝对路径(包括文件名和扩展名)
	 * @param showName 下载后看到的文件名
	 * @return 文件名
	 */
	public static void execute(HttpServletRequest request, HttpServletResponse response, InputStream input, long length, String showName)throws Exception {
		String info = request.getHeader("User-Agent").toLowerCase();
		if (info.contains("firefox")) { // Firefox
			showName = new String(showName.getBytes(), "ISO-8859-1");
		} else { // IE,Chrome
			showName = URLEncoder.encode(showName, "UTF-8");
		}
		response.reset(); // 响应重置
		response.setHeader("Content-Disposition", "attachment; filename=\""+ showName + "\"");
		response.addHeader("Content-Length", ""+length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024*8];
		while ((len = input.read(buffer)) != -1) {
			out.write(buffer,0,len);
		}
		input.close();
		response.flushBuffer();
	}
	public static void execute(HttpServletRequest request,
                               HttpServletResponse response, File file, String showName)
			throws Exception {
		execute(request, response, new FileInputStream(file), file.length(), showName);
	}
	public static void execute(HttpServletRequest request, HttpServletResponse response, String fileAbsPath, String showName) throws Exception{
		execute(request, response, new File(fileAbsPath), showName);
	}
}
