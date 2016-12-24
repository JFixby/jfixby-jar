
package com.jfixby.jar.loader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import com.jfixby.rana.api.asset.AssetsContainer;
import com.jfixby.rana.api.asset.AssetsGroup;
import com.jfixby.rana.api.pkg.PackageHandler;
import com.jfixby.rana.api.pkg.PackageReaderInput;
import com.jfixby.rana.api.pkg.PackageReaderListener;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;

public class JarContainer implements AssetsGroup {
	final ClassLoader loader;

	public JarContainer (final PackageReaderInput input, final ClassLoader parent) throws IOException {

		final File package_root_file = input.getRootFile();
		final PackageReaderListener reader_listener = input.getPackageReaderListener();
		final PackageHandler handler = input.getPackageHandler();
		final AssetsContainer container = input.getStorage();

		final File file = input.getRootFile();
		this.loader = parent;
// loader.addJarFile(file);
// this.loader.addJar(file);

// try {
// final java.io.File jarFile = file.toJavaFile();
// final URLClassLoader urlClassLoader = (URLClassLoader)loader;
// final Method m = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] {URL.class});
// m.setAccessible(true);
// m.invoke(urlClassLoader, jarFile.toURI().toURL());
// String cp = System.getProperty("java.class.path");
// if (cp != null) {
// cp += java.io.File.pathSeparatorChar + jarFile.getCanonicalPath();
// } else {
// cp = jarFile.toURI().getPath();
// }
// System.setProperty("java.class.path", cp);
// } catch (final Exception ex) {
// ex.printStackTrace();
// throw new IOException(ex);
// }

		try {
			final URL url = file.toJavaFile().toURI().toURL();
// super.addURL(url);
			final URLClassLoader classLoader = (URLClassLoader)this.loader;
			final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(classLoader, url);
		} catch (final Exception ex) {
			ex.printStackTrace();
			throw new IOException(ex);
		}

		final Collection<ID> assets = handler.listPackedAssets();
		for (final ID asset : assets) {
			final JavaClassContainer data = new JavaClassContainer(asset, this, this.loader);
			container.addAsset(asset, data);
		}

	}

	@Override
	public void dispose () {
		Err.throwNotImplementedYet();
	}

}
