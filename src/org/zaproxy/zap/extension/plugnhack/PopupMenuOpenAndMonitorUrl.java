/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Copyright 2010 psiinon@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.zaproxy.zap.extension.plugnhack;

import org.parosproxy.paros.Constant;
import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.utils.DesktopUtils;
import org.zaproxy.zap.view.PopupMenuHttpMessage;

public class PopupMenuOpenAndMonitorUrl extends PopupMenuHttpMessage {

	private static final long serialVersionUID = 1L;
	private MonitoredPagesManager mpm = null;

    /**
     * @param label
     */
    public PopupMenuOpenAndMonitorUrl(MonitoredPagesManager mpm) {
        super(Constant.messages.getString("plugnhack.menu.monitor.open"));
        this.mpm = mpm;
    }
    
    public String getParentMenuName() {
    	return Constant.messages.getString("plugnhack.menu.monitor");
    }

    public boolean isSubMenu() {
    	return true;
    }

    
	@Override
	public void performAction(HttpMessage msg) throws Exception {
		this.mpm.setMonitorOnetimeURL(msg.getRequestHeader().getURI());
		DesktopUtils.openUrlInBrowser(msg.getRequestHeader().getURI());
	}
	
	@Override
    public boolean isEnabledForHttpMessage (HttpMessage msg) {
		if (msg == null) {
			return false;
		}
		return DesktopUtils.canOpenUrlInBrowser();
    }

	
	@Override
	public boolean isEnableForInvoker(Invoker invoker) {
		return true;
	}
	
    @Override
    public boolean isSafe() {
    	return true;
    }
}