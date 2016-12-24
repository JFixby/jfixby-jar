
package com.jfixby.jar.loader;

import java.io.IOException;

import com.jfixby.rana.api.asset.Asset;
import com.jfixby.rana.api.asset.AssetsGroup;
import com.jfixby.scarabei.api.assets.ID;

public class JavaClassContainer implements Asset {

	private final ID id;
	private final JarContainer jarContainer;

	public JavaClassContainer (final ID asset, final JarContainer jarContainer, final ClassLoader classLoader) throws IOException {
		this.id = asset;
		this.jarContainer = jarContainer;
// try {
// final Class<?> klass = Class.forName(asset.toString(), true, classLoader);
// } catch (final Throwable e) {
// L.d("not found", asset);
//// e.printStackTrace();
// throw new IOException(e);
// }

	}

	@Override
	public ID getAssetID () {
		return this.id;
	}

	@Override
	public AssetsGroup getGroup () {
		return this.jarContainer;
	}

}
