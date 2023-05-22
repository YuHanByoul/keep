//URL hash tag layerpopup open 추후 삭제 예정
$(document).ready(function() {
	let thisURL = window.location;
	let thisPath = thisURL.pathname;
	let thisHash = thisURL.hash;
	if (thisHash.indexOf('layer') === 1)  {
		thisHash = thisHash.slice(1);
		layerPopup.open({target:thisHash});
	}
	if (thisPath.indexOf('component') === 1)  {
		thisPath = thisPath.replace('/component_', '');
		thisPath = thisPath.replace('.html', '');
		document.title = thisPath;
	}
	if (thisPath.indexOf('UI-C') === 1)  {
		const pageTitle = document.querySelector('.visual-inner h2');
		if (pageTitle !== null) {
			const pageTitleText = pageTitle.innerText;
			document.title = `🚀 ${pageTitleText}`;
		}
	}
	$('body').each(function () {
		var current_path = window.location.pathname;
		var localhost = window.location.href;
		if (localhost.indexOf('http://127.0.0.1:5500') === 0) { //vscode liveServer port
			var vsCode = "vscode://file///C:/KEEP_PORTAL_HTML"; //source file 경로
			var vsCodeHref = '<a class="vscodepath" href=' + '"' + vsCode + current_path + '"></a>';
			$('body').after(vsCodeHref);

			var serverUrl = "http://192.168.0.6:9008"; //source file 경로
			var serverHref = '<a class="serverUrl" href=' + '"' + serverUrl + current_path + '"></a>';
			$('body').after(serverHref);
		}
		if (localhost.indexOf('http://192.168.0.6:9008') === 0) { //vscode liveServer port
			var serverUrl = "http://127.0.0.1:5500"; //source file 경로
			var serverHref = '<a class="serverUrl" href=' + '"' + serverUrl + current_path + '"></a>';
			$('body').after(serverHref);
		}

	})
	
});