
package com.jfixby.jar.packer;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.file.File;

public class JarRanaPackerConfig {

	private final Set<File> inputJars = Collections.newSet();
	private File targetTank;

	public void setOutputTank (final File targetTank) {
		this.targetTank = targetTank;
	}

	public Collection<File> getInputJars () {
		return this.inputJars;
	}

	public File getOutputTank () {
		return this.targetTank;
	}

	public void addInputJars (final List<File> jarsToPack) {
		this.inputJars.addAll(jarsToPack);
	}

}
