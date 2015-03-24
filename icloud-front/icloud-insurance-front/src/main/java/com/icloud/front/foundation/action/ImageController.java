package com.icloud.front.foundation.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.action.BaseController;
import com.icloud.insurance.model.MediaFile;
import com.icloud.insurance.service.MediaFileService;

@Controller
@RequestMapping("/image")
public class ImageController extends BaseController {
	@Resource(name = "mediaFileService")
	protected MediaFileService mediaFileService;

	@RequestMapping("/{imageId}/{width}")
	public void getImage(@PathVariable("imageId") String imageId,
			@PathVariable("width") int width, HttpServletResponse response) {
		MediaFile mediaFile = mediaFileService.getByHashId(imageId);
		byte[] bytes = null;
		if (ICloudUtils.isNotNull(mediaFile)) {
			bytes = mediaFile.getFileBytes();
			response.setContentType(mediaFile.getFileType());
			FileInputStream fis = null;
			try {
				OutputStream out = response.getOutputStream();
				out.write(bytes);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("servlet");
				response.setStatus(302);
				response.setHeader("location", "http://www.baidu.com");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
