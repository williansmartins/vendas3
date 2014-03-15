package br.com.exemplo.vendas.util.locator ;

import java.io.CharArrayWriter ;
import java.util.HashMap ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;
import org.xml.sax.* ;
import org.xml.sax.helpers.DefaultHandler ;

public class ProvidersSaxParser extends DefaultHandler
{
	private static final Log logger ;

	protected CharArrayWriter contents ;

	protected boolean inProvider ;

	protected boolean inProperty ;

	protected HashMap providers ;

	protected Provider provider ;

	protected String propertyName ;

	protected String propertyValue ;

	public ProvidersSaxParser( )
	{
		contents = new CharArrayWriter( ) ;
		inProvider = false ;
		inProperty = false ;
		providers = new HashMap( ) ;
		logger.debug( "ProvidersSaxParser 2.0 - SAX 2.0 Implementation" ) ;
	}

	public HashMap getProviders( )
	{
		return providers ;
	}

	public void startDocument( ) throws SAXException
	{

	}

	public void startElement( String uri, String localName, String qName, Attributes attr )
			throws SAXException
	{
		contents.reset( ) ;
		if (inProvider && "property".equals( qName ))
		{
			inProperty = true ;
			propertyName = null ;
			propertyValue = null ;
			return ;
		}
		if (!inProvider && "provider".equals( qName ))
		{
			inProvider = true ;
			provider = new Provider( ) ;
			return ;
		}
		else
		{
			return ;
		}
	}

	public void endElement( String uri, String localName, String qName ) throws SAXException
	{
		if (inProperty && inProvider && "property".equals( qName ))
		{
			provider.setProperty( propertyName, propertyValue ) ;
			inProperty = false ;
		}
		if (inProperty && inProvider)
		{
			parseProperty( qName, contents.toString( ) ) ;
		}
		else
		{
			if (inProvider)
			{
				parseProvider( qName, contents.toString( ) ) ;
			}

			if (inProvider && "provider".equals( qName ))
			{
				providers.put( provider.getName( ), provider ) ;
				inProvider = false ;
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

	protected void parseProvider( String tagName, String tagValue ) throws SAXParseException
	{
		if ("name".equals( tagName ))
		{
			provider.setName( tagValue ) ;
			return ;
		}
		if ("desc".equals( tagName ))
		{
			provider.setDesc( tagValue ) ;
			return ;
		}
		else
		{
			return ;
		}
	}

	protected void parseProperty( String tagName, String tagValue ) throws SAXParseException
	{
		if ("name".equals( tagName ))
		{
			propertyName = tagValue ;
		}
		if ("value".equals( tagName ))
		{
			propertyValue = tagValue ;
		}
	}

	static
	{
		logger = LogFactory.getLog( ProvidersSaxParser.class ) ;
	}
}
