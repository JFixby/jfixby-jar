
package com.jfixby.jar.loader;

import java.io.IOException;
import java.net.URLClassLoader;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.rana.api.pkg.PackageFormat;
import com.jfixby.rana.api.pkg.PackageHandler;
import com.jfixby.rana.api.pkg.PackageReader;
import com.jfixby.rana.api.pkg.PackageReaderInput;
import com.jfixby.rana.api.pkg.PackageReaderListener;
import com.jfixby.rana.api.pkg.ResourcesManager;
import com.jfixby.rana.api.pkg.StandardPackageFormats;

public class RanaJarLoader implements PackageReader {
	final List<PackageFormat> acceptablePackageFormats;
// private RanaClassLoader ranaLoader;
	private final URLClassLoader classLoader;

	public RanaJarLoader (final ClassLoader classLoader) {
		this.classLoader = (URLClassLoader)Debug.checkNull("classLoader", classLoader);
		this.acceptablePackageFormats = Collections.newList();
		final PackageFormat format = ResourcesManager.newPackageFormat(StandardPackageFormats.Java.Jar);
		this.acceptablePackageFormats.add(format);

// this.ranaLoader = null;
// if (classLoader instanceof RanaClassLoader) {
// this.ranaLoader = (RanaClassLoader)classLoader;
// } else {
// this.ranaLoader = new RanaClassLoader(classLoader);
// }

	}

	@Override
	public Collection<PackageFormat> listAcceptablePackageFormats () {
		return this.acceptablePackageFormats;
	}

	@Override
	public void doReadPackage (final PackageReaderInput input) throws IOException {
		final PackageHandler handler = input.getPackageHandler();
		final PackageReaderListener listener = input.getPackageReaderListener();
		listener.onDependenciesRequired(handler, handler.listDependencies());
		final JarContainer jarContainer = new JarContainer(input, this.classLoader);
	}

}
