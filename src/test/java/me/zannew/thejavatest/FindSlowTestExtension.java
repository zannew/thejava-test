package me.zannew.thejavatest;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

	private long THRESHOLD = 1000L;

	public FindSlowTestExtension(long THRESHOLD) {
		this.THRESHOLD = THRESHOLD;
	}

	@Override
	public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
		ExtensionContext.Store store = getStore(extensionContext);
		store.put("START_TIME", System.currentTimeMillis());
	}

	@Override
	public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
		Method requiredTestMethod = extensionContext.getRequiredTestMethod();
		SlowTest slowTest = requiredTestMethod.getAnnotation(SlowTest.class);
		ExtensionContext.Store store = getStore(extensionContext);
		String testMethodName = extensionContext.getRequiredTestMethod().getName();
		Long start_time = store.remove("START_TIME", long.class);
		long duration = System.currentTimeMillis() - start_time;
		if (duration > THRESHOLD && slowTest == null) {
			System.out.printf("Please consider mark method at [%s] with @SlowTest \n", testMethodName);
		}
	}

	private ExtensionContext.Store getStore(ExtensionContext extensionContext) {
		String testClassName = extensionContext.getRequiredTestClass().getName();
		String testMethodName = extensionContext.getRequiredTestMethod().getName();
		ExtensionContext.Store store = extensionContext.getStore(
			ExtensionContext.Namespace.create(testClassName, testMethodName));
		return store;
	}
}
