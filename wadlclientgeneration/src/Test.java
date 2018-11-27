import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.jvnet.ws.wadl.util.DSDispatcher;
import org.jvnet.ws.wadl.util.JAXBDispatcher;
import org.jvnet.ws.wadl.util.UriBuilder;


public class Test 
{
	 public static class Zodiaccalculator {

	        private JAXBDispatcher _jaxbDispatcher;
	        private DSDispatcher _dsDispatcher;
	        private UriBuilder _uriBuilder;
	        private JAXBContext _jc;
	        private HashMap<String, Object> _templateAndMatrixParameterValues;

	        /**
	         * Create new instance
	         * 
	         */
	        public Zodiaccalculator()
	            throws JAXBException
	        {
	            _dsDispatcher = new DSDispatcher();
	            _uriBuilder = new UriBuilder();
	            List<String> _matrixParamSet;
	            _matrixParamSet = _uriBuilder.addPathSegment("http://localhost:7001/restfullzodiaccalculator/");
	            _matrixParamSet = _uriBuilder.addPathSegment("/zodiaccalculator");
	            _templateAndMatrixParameterValues = new HashMap<String, Object>();
	        }

	        public static class Textget {

	            private JAXBDispatcher _jaxbDispatcher;
	            private DSDispatcher _dsDispatcher;
	            private UriBuilder _uriBuilder;
	            private JAXBContext _jc;
	            private HashMap<String, Object> _templateAndMatrixParameterValues;

	            /**
	             * Create new instance
	             * 
	             */
	            public Textget()
	                throws JAXBException
	            {
	                _dsDispatcher = new DSDispatcher();
	                _uriBuilder = new UriBuilder();
	                List<String> _matrixParamSet;
	                _matrixParamSet = _uriBuilder.addPathSegment("http://localhost:7001/restfullzodiaccalculator/");
	                _matrixParamSet = _uriBuilder.addPathSegment("/zodiaccalculator");
	                _matrixParamSet = _uriBuilder.addPathSegment("/textget");
	                _templateAndMatrixParameterValues = new HashMap<String, Object>();
	            }

	            public void get()
	                throws IOException, MalformedURLException
	            {
	                HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
	                HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
	                String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
	                DataSource _retVal = _dsDispatcher.doGET(_url, _headerParameterValues, null);
	                return ;
	            }

	            public void getAsvoid()
	                throws IOException, MalformedURLException, JAXBException
	            {
	                HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
	                HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
	                String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
	                Object _retVal = _jaxbDispatcher.doGET(_url, _headerParameterValues, null);
	                if (_retVal == null) {
	                    return ;
	                }
	                if (JAXBElement.class.isInstance(_retVal)) {
	                    JAXBElement jaxbElement = ((JAXBElement) _retVal);
	                    _retVal = jaxbElement.getValue();
	                }
	                return ;
	            }

	            public void get(String day, String month, String year)
	                throws IOException, MalformedURLException
	            {
	                HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
	                HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
	                _queryParameterValues.put("day", day);
	                _queryParameterValues.put("month", month);
	                _queryParameterValues.put("year", year);
	                String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
	                DataSource _retVal = _dsDispatcher.doGET(_url, _headerParameterValues, null);
	                System.out.println(_retVal.getName());
	                return ;
	            }

	            public void getAsvoid(String day, String month, String year)
	                throws IOException, MalformedURLException, JAXBException
	            {
	                HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
	                HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
	                _queryParameterValues.put("day", day);
	                _queryParameterValues.put("month", month);
	                _queryParameterValues.put("year", year);
	                String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
	                Object _retVal = _jaxbDispatcher.doGET(_url, _headerParameterValues, null);
	                if (_retVal == null) {
	                    return ;
	                }
	                if (JAXBElement.class.isInstance(_retVal)) {
	                    JAXBElement jaxbElement = ((JAXBElement) _retVal);
	                    _retVal = jaxbElement.getValue();
	                }
	                System.out.println(_retVal);
	                return ;
	            }

	        }
	 }       
	public static void main(String[] args) throws Exception
	{
		new Zodiaccalculator.Textget().getAsvoid("27", "07", "1977");
		
//		HttpLocalhost7001Restfullzodiaccalculator zocl = new HttpLocalhost7001Restfullzodiaccalculator();
	}

}
