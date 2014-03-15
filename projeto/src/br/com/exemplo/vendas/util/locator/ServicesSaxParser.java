package br.com.exemplo.vendas.util.locator ;

import java.io.CharArrayWriter ;
import java.util.HashMap ;
import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;
import org.xml.sax.* ;
import org.xml.sax.helpers.DefaultHandler ;

public class ServicesSaxParser extends DefaultHandler
{
	private static final Log logger ;

	protected CharArrayWriter contents ;

	protected boolean inService ;

	protected boolean inProviders ;

	protected HashMap services ;

	protected Service service ;

	protected String propertyName ;

	protected String propertyValue ;

	public ServicesSaxParser( )
	{
		contents = new CharArrayWriter( ) ;
		inService = false ;
		inProviders = false ;
		services = new HashMap( ) ;
		logger.debug( "ServicesSaxParser 2.0 - SAX 2.0 Implementation" ) ;
	}

	public HashMap getServices( )
	{
		return services ;
	}

	public void startDocument( ) throws SAXException
	{

	}

	public void startElement( String uri, String localName, String qName, Attributes attr )
			throws SAXException
	{
		contents.reset( ) ;
		if (inService && "providers".equals( qName ))
		{
			inProviders = true ;
			return ;
		}
		if (!inService && "service".equals( qName ))
		{
			inService = true ;
			service = new Service( ) ;
			return ;
		}
		else
		{
			return ;
		}
	}

	public void endElement( String uri, String localName, String qName ) throws SAXException
	{
		if (inProviders && inService && "providers".equals( qName ))
		{
			inProviders = false ;
		}

		if (inProviders && inService)
		{
			parseProvider( qName, contents.toString( ) ) ;
		}
		else
		{
			if (inService)
			{
				parseService( qName, contents.toString( ) ) ;
			}

			if (inService && "service".equals( qName ))
			{
				services.put( service.getName( ), service ) ;
				inService = false ;
			}
		}
	}

	public void characters( char ch[], int start, int length ) throws SAXException
	{
		contents.write( ch, start, length ) ;
	}

	public void endDocument( ) throws SAXException
	{

	}

	public void fatalError( SAXParseException _saxpex ) throws SAXException
	{
		logger.fatal( "Fatal Error: [" + _saxpex.getMessage( ) + "].", _saxpex ) ;
	}

	public void error( SAXParseException _saxpex ) throws SAXException
	{
		logger.error( "Error: [" + _saxpex.getMessage( ) + "].", _saxpex ) ;
	}

	public void warning( SAXParseException _saxpex ) throws SAXException
	{
		logger.warn( "Warning: [" + _saxpex.getMessage( ) + "].", _saxpex ) ;
	}

	protected void parseService( String tagName, String tagValue ) throws SAXParseException
	{
		if ("name".equals( tagName ))
		{
			service.setName( tagValue ) ;
			return ;
		}
		if ("jndi-name".equals( tagName ))
		{
			service.setJndiName( tagValue ) ;
			return ;
		}
		if ("cacheable".equals( tagName ))
		{
			service.setCacheable( Boolean.valueOf( tagValue ).booleanValue( ) ) ;
			return ;
		}
		else
		{
			return ;
		}
	}

	protected void parseProvider( String tagName, String tagValue ) throws SAXParseException
	{
		if ("name".equals( tagName ))
		{
			service.addProvider( tagValue ) ;
		}
	}

	static
	{
		logger = LogFactory.getLog( ServicesSaxParser.class ) ;
	}
}
