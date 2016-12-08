
package com.jfixby.jar.loader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import com.jfixby.cmns.api.file.File;

public class RanaURLClassLoader extends URLClassLoader {

	public RanaURLClassLoader (final ClassLoader parent) {
		super(new URL[0], parent);
	}

	public void addJar (final File file) throws IOException {
		try {
			final URL url = file.toJavaFile().toURI().toURL();
			super.addURL(url);
// final URLClassLoader classLoader = loader;
// final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
// method.setAccessible(true);
// method.invoke(classLoader, url);
		} catch (final Exception ex) {
			ex.printStackTrace();
			throw new IOException(ex);
		}

	}

}
