package my.demo.route;
import org.apache.camel.builder.RouteBuilder;
import my.demo.constant.Constant;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class EntryRoute extends RouteBuilder{
	@Override
	public void configure() throws Exception {
		from(Constant.ENTRY).streamCaching()
		.process(Constant.INIT_PROCESSOR)
		.bean(Constant.BEAN_LOG, "input")
		.choice()
			.when(exchangeProperty(Constant.HEADER_METHOD).isEqualTo("create"))
				.log("create")
				.to(Constant.DIRECT_CREATE)
			.when(exchangeProperty(Constant.HEADER_METHOD).isEqualTo("delete"))
				.log("delete")
				.to(Constant.DIRECT_DELETE)
			.when(exchangeProperty(Constant.HEADER_METHOD).isEqualTo("query"))
				.log("query")
				.to(Constant.DIRECT_QUERY)
			.otherwise()
				.log("default")
				.to(Constant.DIRECT_QUERY)
			.end()
		.bean(Constant.BEAN_LOG, "output")
	    .end();
		
		
		from(Constant.DIRECT_INIT).routeId(Constant.DIRECT_INIT)
			.doTry()
				.process(Constant.INIT_PROCESSOR)
			.doCatch(Exception.class)
				.to(Constant.DIRECT_ERROR)
			.stop()
	    .end();
		
		from(Constant.DIRECT_CREATE).routeId(Constant.DIRECT_CREATE)
			.doTry()
				.process(Constant.CREATE_PROCESSOR)
			.doCatch(Exception.class)
				.to(Constant.DIRECT_ERROR)
			.stop()
	    .end();
		
		from(Constant.DIRECT_DELETE).routeId(Constant.DIRECT_DELETE)
			.doTry()
				.process(Constant.DELETE_PROCESSOR)
			.doCatch(Exception.class)
				.to(Constant.DIRECT_ERROR)
			.stop()
	    .end();
		
		from(Constant.DIRECT_QUERY).routeId(Constant.DIRECT_QUERY)
			.doTry()
				.process(Constant.QUERY_PROCESSOR)
			.doCatch(Exception.class)
				.to(Constant.DIRECT_ERROR)
			.stop()
	    .end();
		
		
		from(Constant.DIRECT_ERROR).routeId(Constant.DIRECT_ERROR)
			.bean(Constant.BEAN_LOG, "error")
		.end();

	}
}
