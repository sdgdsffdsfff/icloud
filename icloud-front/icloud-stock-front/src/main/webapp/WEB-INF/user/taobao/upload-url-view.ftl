<#import "/icloud/icloud-layer-window.ftl" as ilw/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<@ilw.layerwindow jsFiles=['swfupload/swfupload.js','swfupload/handlers.js','icloud/uploadUrl.js'] cssFiles=['swtupload/default.css'] >
		<div id="content">
			<form>
				<div style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
					<span id="spanButtonPlaceholder"></span>
				</div>&nbsp;&nbsp;
				<input id="btnUpload" type="button" value="上  传"
						onclick="startUploadFile();" class="btn3_mouseout" />&nbsp;
				<input id="btnCancel" type="button" value="取消所有上传"
						onclick="cancelUpload();" disabled="disabled" class="btn3_mouseout" />
			</form>
			<div id="divFileProgressContainer"></div>
			<div id="thumbnails">
				<table id="infoTable" border="0" width="530" style="display: inline;  padding: 2px;margin-top:8px;">
				</table>
			</div>
		</div>	
</@ilw.layerwindow>