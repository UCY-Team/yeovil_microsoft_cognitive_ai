package models.modules;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public enum Module
{
	TEST("Test", "/WEB-INF/modules/test.html"),
	WEBCAM("Webcam", "/WEB-INF/modules/webcam.html", "js/webcam.js", "css/webcam.css"),
	CONTROLS("Controls", "/WEB-INF/modules/controls.jsp", "js/controls.js"),
	OUTPUT_LOG("Output Log", "/WEB-INF/modules/output_log.html", "js/outputLog.js", "css/output_log.css"),
	QR_CODE("QR-Code", "/WEB-INF/modules/qrcode.html", "./css/qrcode.css");
	
	private final String name;
	private final String contentPath;
	private final Set<String> externalPaths;
	
	/**
	 * Class Constructor.
	 *
	 * @param name - the name of this module
	 * @param contentPath - the path of the jsp file to include in this module.
	 */
	private Module(final String name, final String contentPath)
	{
		this.name = name;
		this.contentPath = contentPath;
		this.externalPaths = Collections.emptySet();
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param name - the name of this module
	 * @param contentPath - the path of the jsp file to include in this module.
	 * @param externalResource - the path of the javascript/css file required by this module.
	 */
	private Module(final String name, final String contentPath, final String externalResource)
	{
		this.name = name;
		this.contentPath = contentPath;
		this.externalPaths = Collections.singleton(externalResource);
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param name - the name of this module
	 * @param contentPath - the path of the jsp file to include in this module.
	 * @param externalResources - the paths of any javascript or css files required by this module.
	 */
	private Module(final String name, final String contentPath, final String... externalResources)
	{
		this(name,contentPath, Arrays.asList(externalResources));
	}
	
	/**
	 * Class Constructor.
	 *
	 * @param name - the name of this module
	 * @param contentPath - the path of the jsp file to include in this module.
	 * @param externalResources - the paths of any javascript or css files required by this module.
	 */
	private Module(final String name, final String contentPath, final Collection<? extends String> externalResources)
	{
		this.name = name;
		this.contentPath = contentPath;
		this.externalPaths = Collections.unmodifiableSet(new LinkedHashSet<>(externalResources));
	}
	
	public static Module fromName(final String name)
	{
		for (final Module module : Module.values())
		{
			if (module.getName().equals(name))
			{
				return module;
			}
		}
		return null;
	}
	
	/**
	 * @return the name of this module.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * @return the path of the jsp file to include as this modules content.
	 */
	public String getContentpath()
	{
		return this.contentPath;
	}
	
	/**
	 * @return the paths of any javascript files this module uses.
	 */
	public Set<String> getExternalPaths()
	{
		return this.externalPaths;
	}
}
