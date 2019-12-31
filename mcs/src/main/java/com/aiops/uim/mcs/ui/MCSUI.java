package com.aiops.uim.mcs.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import com.aiops.uim.mcs.models.Template;
import com.nimsoft.selfservice.v2.model.Template;
import com.aiops.uim.mcs.serviceclient.IGroupService;
import com.aiops.uim.mcs.serviceclient.ITemplateService;
import com.aiops.uim.mcs.services.GroupService;
import com.aiops.uim.mcs.services.TemplateService;
import com.aiops.uim.mcs.utils.UIMInstance;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MCSUI extends UI {
	
	HorizontalSplitPanel layout = new HorizontalSplitPanel();
	TabSheet deviceTabSheet = new TabSheet();
	
	Grid<Template> deviceTemplatesGrid = new Grid<>(Template.class);
	FormLayout templateDetailsForm;	

	//TODO: Need to get this information from ecosystem
	//private UIMInstance uimInstance = new UIMInstance("http", "kp642490-ump-e4", 80, "administrator", "N0tallowed");
	private UIMInstance uimInstance = new UIMInstance("http", "10.17.175.145", 80, "administrator", "1qaz2wsx");
	
    @SuppressWarnings("unchecked")
	@Override
    protected void init(VaadinRequest vaadinRequest) {    	    	
    	try {	   
    		
	    	//Setting layout
	    	layout.setSplitPosition(25);
	    	layout.setFirstComponent(displayGroups());
	    	layout.setSecondComponent(createTabLayout());    	
	    	setContent(layout);   	
		} catch (ParseException e) {			
			e.printStackTrace();
		}
    }
    
    //Display device templates
    private List<Template> getTemplatesByDevice(int deviceId) throws ParseException{
    	
    	ITemplateService templateService = new TemplateService(uimInstance);
		return templateService.getAllTemplatesByDevice(deviceId);
		
    	/*String deviceTemplates = "[{\"templateName\":\"Application Discovery Scripts\",\"templateId\":228,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.3\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.PluginDeployer\",\"probe\":\"attr_publisher\",\"probeversion\":null,\"maxprofiles\":500,\"description\":\"Application Discovery scripts package\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":\"USM Monitoring Configuration Service\",\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"plugin_package\",\"directives\":\"skiprestartprobe\",\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"CPU Monitor\",\"templateId\":153,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"2.54\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":1,\"description\":\"Monitor CPU usage\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"CPU Monitor (Enhanced)\",\"templateId\":147,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"6.42.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.42-MC\",\"maxprofiles\":1,\"description\":\"Monitor CPU usage\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"CPU Monitor\",\"dependentTemplates\":null},{\"templateName\":\"Default Disk(s)\",\"templateId\":148,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"2.56\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":1,\"description\":null,\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":null,\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":\"removesection\",\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Default Disk(s) (Enhanced)\",\"templateId\":154,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"6.42.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.42-MC\",\"maxprofiles\":1,\"description\":null,\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":null,\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":\"removesection\",\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Default Disk(s)\",\"dependentTemplates\":null},{\"templateName\":\"Disk IO Monitors\",\"templateId\":156,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.15\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":1,\"description\":\"Monitor disk throughput\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Disk IO Monitors (Enhanced)\",\"templateId\":136,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"6.42.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.42-MC\",\"maxprofiles\":1,\"description\":\"Monitor disk throughput\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Disk IO Monitors\",\"dependentTemplates\":null},{\"templateName\":\"Memory Monitor\",\"templateId\":150,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"2.48\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":1,\"description\":\"Monitor memory usage\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Memory Monitor (Enhanced)\",\"templateId\":157,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"6.42.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.42-MC\",\"maxprofiles\":1,\"description\":\"Monitor memory usage\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Memory Monitor\",\"dependentTemplates\":null},{\"templateName\":\"Network Connectivity (Enhanced)\",\"templateId\":160,\"monitoringagenttemplatename\":\"Setup Network Connectivity (Enhanced)\",\"monitoringagentresolution\":null,\"version\":\"3.40.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"net_connect\",\"probeversion\":null,\"maxprofiles\":1,\"description\":\"Monitor the availability of network applications remotely on your system\",\"production\":true,\"remote\":true,\"type\":\"device\",\"icon\":\"Network\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"{device.name}\",\"directives\":\"removesection\",\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Port Check\",\"dependentTemplates\":null},{\"templateName\":\"NIC Monitor\",\"templateId\":151,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.23\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":1,\"description\":\"Monitor network interface usage\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"NIC Monitor (Enhanced)\",\"templateId\":152,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"6.42.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.42-MC\",\"maxprofiles\":1,\"description\":\"Monitor network interface usage\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"NIC Monitor\",\"dependentTemplates\":null},{\"templateName\":\"Port Check\",\"templateId\":169,\"monitoringagenttemplatename\":\"Setup net_connect\",\"monitoringagentresolution\":null,\"version\":\"1.27\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"net_connect\",\"probeversion\":null,\"maxprofiles\":1,\"description\":\"Monitor the availability of network applications remotely on your system\",\"production\":true,\"remote\":true,\"type\":\"device\",\"icon\":\"Network\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":\"removesection\",\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Remote System Monitoring\",\"templateId\":43,\"monitoringagenttemplatename\":\"Setup rsp\",\"monitoringagentresolution\":null,\"version\":\"1.9\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"rsp\",\"probeversion\":\"5.20\",\"maxprofiles\":500,\"description\":null,\"production\":true,\"remote\":true,\"type\":\"device\",\"icon\":null,\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Remote System Monitoring (Enhanced)\",\"templateId\":186,\"monitoringagenttemplatename\":\"Setup RSP (Enhanced)\",\"monitoringagentresolution\":null,\"version\":\"5.35.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"rsp\",\"probeversion\":\"5.33\",\"maxprofiles\":500,\"description\":null,\"production\":true,\"remote\":true,\"type\":\"device\",\"icon\":null,\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Remote System Monitoring\",\"dependentTemplates\":null},{\"templateName\":\"Setup cdm\",\"templateId\":159,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.46\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":1,\"description\":null,\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":null,\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Setup cdm (Enhanced)\",\"templateId\":158,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"6.42.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.42-MC\",\"maxprofiles\":1,\"description\":null,\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":null,\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Setup cdm\",\"dependentTemplates\":null},{\"templateName\":\"Setup net_connect\",\"templateId\":167,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.38\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"net_connect\",\"probeversion\":null,\"maxprofiles\":1,\"description\":\"Setup for network ping and TCP service connection monitoring\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Interface\",\"parent\":0,\"acl\":\"Account Administration\",\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Setup Network Connectivity (Enhanced)\",\"templateId\":168,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"3.40.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"net_connect\",\"probeversion\":null,\"maxprofiles\":1,\"description\":\"Setup for network ping and TCP service connection monitoring\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Interface\",\"parent\":0,\"acl\":\"Account Administration\",\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":null,\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Setup net_connect\",\"dependentTemplates\":null},{\"templateName\":\"Setup rsp\",\"templateId\":32,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.11\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"rsp\",\"probeversion\":\"5.20\",\"maxprofiles\":1,\"description\":null,\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":\"Account Administration\",\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null},{\"templateName\":\"Setup RSP (Enhanced)\",\"templateId\":170,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"5.35.1\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"rsp\",\"probeversion\":\"5.33\",\"maxprofiles\":1,\"description\":null,\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Server\",\"parent\":0,\"acl\":\"Account Administration\",\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"profilename\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":\"Setup rsp\",\"dependentTemplates\":null},{\"templateName\":\"Shared Disk(s)\",\"templateId\":142,\"monitoringagenttemplatename\":null,\"monitoringagentresolution\":null,\"version\":\"1.9\",\"deploymethod\":\"com.nimsoft.selfservice.v2.deployer.NimsoftProbeDeployer\",\"probe\":\"cdm\",\"probeversion\":\"6.30-MC\",\"maxprofiles\":500,\"description\":\"Disk usage monitoring\",\"production\":true,\"remote\":false,\"type\":\"device\",\"icon\":\"Disk\",\"parent\":0,\"acl\":null,\"globalcontexts\":null,\"isComposite\":false,\"validationreport\":null,\"profileNameVariable\":\"test_disk\",\"directives\":null,\"container\":null,\"subprofiles\":null,\"templateFilters\":null,\"legacyTemplateName\":null,\"dependentTemplates\":null}]";
    	JSONParser parser = new JSONParser();
    	JSONArray templates = (JSONArray) parser.parse(deviceTemplates);
    	ArrayList<Template> templatesArrayList = new ArrayList<>();    	
				
    	for(int i = 0; i < templates.size(); i++) {
		  JSONObject field = (JSONObject) templates.get(i);
		  if(field.containsKey("templateName")) {
			  String templateName = field.get("templateName").toString();
			  int templateId = Integer.parseInt(field.get("templateId").toString());				  
			  //Template template = new Template(templateName, templateId);
			  Template template = new Template();
			  templatesArrayList.add(template);
		  }
    	}
    	return templatesArrayList;*/
	}
    
    //Get template by template id
    private JSONObject getTemplateById(int templateId) throws ParseException {
		
    	String templateContent = "{ \"templatename\": \"Setup cdm (Enhanced)\", \"templateid\": 158, \"name\": \"Setup cdm (Enhanced)\", \"cs_id\": 1, \"groupId\": null, \"poller\": 1, \"field\": [ { \"id\": 56847, \"type\": \"text\", \"name\": \"spooler_metrics_plugin_version\", \"helptext\": null, \"label\": \"Version\", \"defaultvalue\": \"7.9.5\"}, { \"id\": 56862, \"type\": \"combobox\", \"name\": \"loglevel (/steve/setup)\", \"label\": \"Log Level\", \"helptext\": \"Select the level of detail written to the log file. Log as little as possible during normal operation, to minimize disk consumption.\", \"values\": [ { \"displayvalue\": \"1 - Error\", \"value\": \"1\", \"attributes\": { \"displayValue\": \"1 - Error\", \"field\": \"56862\", \"id\": \"71598\", \"value\": \"1\" } }, { \"displayvalue\": \"0 - Fatal\", \"value\": \"0\", \"attributes\": { \"displayValue\": \"0 - Fatal\", \"field\": \"56862\", \"id\": \"71597\", \"value\": \"0\" } }, { \"displayvalue\": \"2 - Warning\", \"value\": \"2\", \"attributes\": { \"displayValue\": \"2 - Warning\", \"field\": \"56862\", \"id\": \"71599\", \"value\": \"2\" } }, { \"displayvalue\": \"3 - Info\", \"value\": \"3\", \"attributes\": { \"displayValue\": \"3 - Info\", \"field\": \"56862\", \"id\": \"71600\", \"value\": \"3\" } }, { \"displayvalue\": \"4 - Debug\", \"value\": \"4\", \"attributes\": { \"displayValue\": \"4 - Debug\", \"field\": \"56862\", \"id\": \"71601\", \"value\": \"4\" } }, { \"displayvalue\": \"5 - Trace\", \"value\": \"5\", \"attributes\": { \"displayValue\": \"5 - Trace\", \"field\": \"56862\", \"id\": \"71602\", \"value\": \"5\" } } ] }, { \"id\": 56869, \"type\": \"text\", \"name\": \"logsize\", \"helptext\": null, \"label\": \"Log size\", \"defaultvalue\": \"10240\"}, { \"id\": 56849, \"type\": \"checkbox\", \"name\": \"alarm_on_each_sample (/steve/setup)\", \"label\": \"Send Alarm on Each Sample\", \"labelposition\": null, \"helptext\": \"Select me if you can.\" }, { \"id\": 56878, \"type\": \"combobox\", \"name\": \"paging_in_kilobytes (/steve/setup)\", \"label\": \"Paging Measured In\", \"labelposition\": null, \"helptext\": \"Paging is the amount of memory sent to, or read from, virtual memory. Select the paging measurement unit.\", \"values\": [ { \"displayvalue\": \"Kilobytes per second\", \"value\": \"yes\", \"attributes\": { \"displayValue\": \"Kilobytes per second\", \"field\": \"56878\", \"id\": \"71619\", \"value\": \"yes\" } }, { \"displayvalue\": \"Pages per second\", \"value\": \"no\", \"attributes\": { \"displayValue\": \"Pages per second\", \"field\": \"56878\", \"id\": \"71620\", \"value\": \"no\" } } ] }, ], \"accountid\": null, \"parentprofileid\": null, \"status\": \"new\", \"ancestorprofileId\": null, \"profile\": null, \"priority\": null, \"foreachField\": null, \"foreachVariable\": null, \"foreachValueRegex\": null, \"conditionalprofilename\": null, \"profileNameVariable\": \"profilename\", \"type\": \"device\", \"icon\": null }";
    	JSONParser parser = new JSONParser();
    	JSONObject json = (JSONObject) parser.parse(templateContent); 
    	return json;
    	
    }    
    
    //Display groups in tree view
    public Tree<String> displayGroups() {
    	// An initial planet tree
    	Tree<String> tree = new Tree<>();
    	TreeData<String> treeData = new TreeData<>();

    	treeData.addItem(null, "Groups");
    	treeData.addItem(null, "Geo View");
    	treeData.addItem(null, "Inventory");
    	treeData.addItem(null, "Search Results");
    	
    	treeData.addItem("Groups","Application Discovery");
    	treeData.addItem("Groups","Operating Systems");

    	// Items with hierarchy
    	treeData.addItem("Application Discovery","Discovered Application Systems");
    	treeData.addItem("Application Discovery","Setup Application Discovery");
    	
    	treeData.addItem("Operating Systems","Windows");
    	treeData.addItem("Windows","kp642490-uim-e4");
    	treeData.addItem("Windows","kp642490-ump-e4");

    	TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(treeData);
    	tree.setDataProvider(inMemoryDataProvider);
    	tree.expand("Groups"); 
    	
    	groupsTreeClickHandler(tree);
    	
    	return tree;
    }
    
  //Display tab layout for a device
    public TabSheet createTabLayout() throws ParseException {
    	
    	VerticalLayout tabDetails = new VerticalLayout();    	
    	deviceTabSheet.addTab(tabDetails, "Details");
    	
    	VerticalLayout tabAlarms = new VerticalLayout();    	
    	deviceTabSheet.addTab(tabAlarms, "Alarms");
    	
    	VerticalLayout tabMetrics = new VerticalLayout();    	
    	deviceTabSheet.addTab(tabMetrics, "Metrics");
    	
    	VerticalLayout tabMonitoring = new VerticalLayout();    	
    	constructMonitoringData(tabMonitoring);    	
    	deviceTabSheet.addTab(tabMonitoring, "Monitoring");
    	
    	VerticalLayout tabReports = new VerticalLayout();    	
    	deviceTabSheet.addTab(tabReports, "Reports");
    	
    	
    	// Handling tab changes
    	deviceTabSheet.addSelectedTabChangeListener(e -> {
    	    Component selected = deviceTabSheet.getSelectedTab();
    	    VerticalLayout tab = (VerticalLayout) selected;	        
    	});
    	
    	return deviceTabSheet;
    }
    
    //Populate monitoring tab
    private void constructMonitoringData(VerticalLayout tabMonitoring) throws ParseException {
    	int deviceId = 1;
    	HorizontalSplitPanel monitoringPanel = new HorizontalSplitPanel();
    	
    	//Populate device templates
		deviceTemplatesGrid.setItems(getTemplatesByDevice(deviceId));
		/*deviceTemplatesGrid.getColumn("name").setCaption("Templates");
    	Grid.Column column = deviceTemplatesGrid.getColumn("id");
    	column.setHidden(true);*/
    	
    	//Handle template click event
    	handleTemplateEvents(deviceTemplatesGrid, monitoringPanel);
    	
    	//Setting monitoring tab layout
    	monitoringPanel.setSizeFull();
    	monitoringPanel.setSplitPosition(25);
    	monitoringPanel.setFirstComponent(deviceTemplatesGrid);    	
    	tabMonitoring.addComponent(monitoringPanel);
    }
    
    private void handleTemplateEvents(Grid<Template> grid, HorizontalSplitPanel monitoringPanel) {
    	templateDetailsForm = new FormLayout();    		    	
    	grid.asSingleSelect().addValueChangeListener(e ->{
    		try {
				JSONObject template = getTemplateById(1);//e.getValue().id);
				if(template.containsKey("field")) {
					JSONArray fields =  (JSONArray) template.get("field");					
				  for(int i = 0; i < fields.size(); i++) {					  
					  JSONObject field = (JSONObject) fields.get(i);
					  if(field.containsKey("type")) {
						  if(field.get("type").toString().equals("text")) {
							  TextField tf1 = new TextField("Name");							  
							  //tf1.setRequiredIndicatorVisible(true);
							  tf1.setCaption(field.get("label").toString());
							  tf1.setValue(field.get("defaultvalue").toString());
							  tf1.setId(field.get("id").toString());							  
							  templateDetailsForm.addComponent(tf1);
						  }
						  else if(field.get("type").toString().equals("checkbox")) {							  
							  CheckBox cb = new CheckBox();	
							  cb.setCaption(field.get("label").toString());
							  templateDetailsForm.addComponent(cb);
						  }
						  else if(field.get("type").toString().equals("combobox")) {							  
							  
							  JSONArray comboItems =  (JSONArray) field.get("values");
							  List<String> items = new ArrayList<>();							  
							  for(int j = 0;j<comboItems.size(); j++) {
								  JSONObject comboItem = (JSONObject) comboItems.get(j);
								  items.add(comboItem.get("displayvalue").toString());
							  }
							  ComboBox cob = new ComboBox("",items);	
							  cob.setCaption(field.get("label").toString());
							  templateDetailsForm.addComponent(cob);
						  }
					  }
					  
				  }
				}
					
				monitoringPanel.setSecondComponent(templateDetailsForm); 		    	
		    	templateDetailsForm.setMargin(true);
		    	
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    }
    
    private void groupsTreeClickHandler(Tree<String> tree) {
    	tree.addItemClickListener(e ->{
    		Notification.show(e.getItem(), Notification.Type.HUMANIZED_MESSAGE);
    	});
//    	tree.addItemClickListener(
//		  new ItemClickEvent.ItemClickListener() {
//		    public void itemClick(ItemClickEvent event) {
//		        // Pick only left mouse clicks
//		        if (event.getButton() == ItemClickEvent.BUTTON_LEFT)
//		            Notification.show("Left click",
//		                        Notification.Type.HUMANIZED_MESSAGE);
//		    }
//		  });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MCSUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
