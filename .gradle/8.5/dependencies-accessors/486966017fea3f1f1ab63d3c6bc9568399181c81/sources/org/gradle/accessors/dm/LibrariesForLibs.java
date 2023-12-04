package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final JunitLibraryAccessors laccForJunitLibraryAccessors = new JunitLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at io
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Returns the group of libraries at junit
     */
    public JunitLibraryAccessors getJunit() {
        return laccForJunitLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoCucumberLibraryAccessors laccForIoCucumberLibraryAccessors = new IoCucumberLibraryAccessors(owner);
        private final IoGithubLibraryAccessors laccForIoGithubLibraryAccessors = new IoGithubLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.cucumber
         */
        public IoCucumberLibraryAccessors getCucumber() {
            return laccForIoCucumberLibraryAccessors;
        }

        /**
         * Returns the group of libraries at io.github
         */
        public IoGithubLibraryAccessors getGithub() {
            return laccForIoGithubLibraryAccessors;
        }

    }

    public static class IoCucumberLibraryAccessors extends SubDependencyFactory {
        private final IoCucumberCucumberLibraryAccessors laccForIoCucumberCucumberLibraryAccessors = new IoCucumberCucumberLibraryAccessors(owner);

        public IoCucumberLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.cucumber.cucumber
         */
        public IoCucumberCucumberLibraryAccessors getCucumber() {
            return laccForIoCucumberCucumberLibraryAccessors;
        }

    }

    public static class IoCucumberCucumberLibraryAccessors extends SubDependencyFactory {

        public IoCucumberCucumberLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (io.cucumber:cucumber-java)
         * with versionRef 'io.cucumber.cucumber.java'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava() {
                return create("io.cucumber.cucumber.java");
        }

            /**
             * Creates a dependency provider for junit (io.cucumber:cucumber-junit)
         * with versionRef 'io.cucumber.cucumber.junit'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() {
                return create("io.cucumber.cucumber.junit");
        }

    }

    public static class IoGithubLibraryAccessors extends SubDependencyFactory {
        private final IoGithubBonigarciaLibraryAccessors laccForIoGithubBonigarciaLibraryAccessors = new IoGithubBonigarciaLibraryAccessors(owner);

        public IoGithubLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.github.bonigarcia
         */
        public IoGithubBonigarciaLibraryAccessors getBonigarcia() {
            return laccForIoGithubBonigarciaLibraryAccessors;
        }

    }

    public static class IoGithubBonigarciaLibraryAccessors extends SubDependencyFactory {

        public IoGithubBonigarciaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for webdrivermanager (io.github.bonigarcia:webdrivermanager)
         * with versionRef 'io.github.bonigarcia.webdrivermanager'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWebdrivermanager() {
                return create("io.github.bonigarcia.webdrivermanager");
        }

    }

    public static class JunitLibraryAccessors extends SubDependencyFactory {

        public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (junit:junit)
         * with versionRef 'junit.junit'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() {
                return create("junit.junit");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgAssertjLibraryAccessors laccForOrgAssertjLibraryAccessors = new OrgAssertjLibraryAccessors(owner);
        private final OrgSeleniumhqLibraryAccessors laccForOrgSeleniumhqLibraryAccessors = new OrgSeleniumhqLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.assertj
         */
        public OrgAssertjLibraryAccessors getAssertj() {
            return laccForOrgAssertjLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.seleniumhq
         */
        public OrgSeleniumhqLibraryAccessors getSeleniumhq() {
            return laccForOrgSeleniumhqLibraryAccessors;
        }

    }

    public static class OrgAssertjLibraryAccessors extends SubDependencyFactory {
        private final OrgAssertjAssertjLibraryAccessors laccForOrgAssertjAssertjLibraryAccessors = new OrgAssertjAssertjLibraryAccessors(owner);

        public OrgAssertjLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.assertj.assertj
         */
        public OrgAssertjAssertjLibraryAccessors getAssertj() {
            return laccForOrgAssertjAssertjLibraryAccessors;
        }

    }

    public static class OrgAssertjAssertjLibraryAccessors extends SubDependencyFactory {

        public OrgAssertjAssertjLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.assertj:assertj-core)
         * with versionRef 'org.assertj.assertj.core'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("org.assertj.assertj.core");
        }

    }

    public static class OrgSeleniumhqLibraryAccessors extends SubDependencyFactory {
        private final OrgSeleniumhqSeleniumLibraryAccessors laccForOrgSeleniumhqSeleniumLibraryAccessors = new OrgSeleniumhqSeleniumLibraryAccessors(owner);

        public OrgSeleniumhqLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.seleniumhq.selenium
         */
        public OrgSeleniumhqSeleniumLibraryAccessors getSelenium() {
            return laccForOrgSeleniumhqSeleniumLibraryAccessors;
        }

    }

    public static class OrgSeleniumhqSeleniumLibraryAccessors extends SubDependencyFactory {
        private final OrgSeleniumhqSeleniumSeleniumLibraryAccessors laccForOrgSeleniumhqSeleniumSeleniumLibraryAccessors = new OrgSeleniumhqSeleniumSeleniumLibraryAccessors(owner);

        public OrgSeleniumhqSeleniumLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.seleniumhq.selenium.selenium
         */
        public OrgSeleniumhqSeleniumSeleniumLibraryAccessors getSelenium() {
            return laccForOrgSeleniumhqSeleniumSeleniumLibraryAccessors;
        }

    }

    public static class OrgSeleniumhqSeleniumSeleniumLibraryAccessors extends SubDependencyFactory {

        public OrgSeleniumhqSeleniumSeleniumLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (org.seleniumhq.selenium:selenium-java)
         * with versionRef 'org.seleniumhq.selenium.selenium.java'.
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava() {
                return create("org.seleniumhq.selenium.selenium.java");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final JunitVersionAccessors vaccForJunitVersionAccessors = new JunitVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.junit
         */
        public JunitVersionAccessors getJunit() {
            return vaccForJunitVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoCucumberVersionAccessors vaccForIoCucumberVersionAccessors = new IoCucumberVersionAccessors(providers, config);
        private final IoGithubVersionAccessors vaccForIoGithubVersionAccessors = new IoGithubVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.cucumber
         */
        public IoCucumberVersionAccessors getCucumber() {
            return vaccForIoCucumberVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.io.github
         */
        public IoGithubVersionAccessors getGithub() {
            return vaccForIoGithubVersionAccessors;
        }

    }

    public static class IoCucumberVersionAccessors extends VersionFactory  {

        private final IoCucumberCucumberVersionAccessors vaccForIoCucumberCucumberVersionAccessors = new IoCucumberCucumberVersionAccessors(providers, config);
        public IoCucumberVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.cucumber.cucumber
         */
        public IoCucumberCucumberVersionAccessors getCucumber() {
            return vaccForIoCucumberCucumberVersionAccessors;
        }

    }

    public static class IoCucumberCucumberVersionAccessors extends VersionFactory  {

        public IoCucumberCucumberVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: io.cucumber.cucumber.java (7.14.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJava() { return getVersion("io.cucumber.cucumber.java"); }

            /**
             * Returns the version associated to this alias: io.cucumber.cucumber.junit (7.14.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("io.cucumber.cucumber.junit"); }

    }

    public static class IoGithubVersionAccessors extends VersionFactory  {

        private final IoGithubBonigarciaVersionAccessors vaccForIoGithubBonigarciaVersionAccessors = new IoGithubBonigarciaVersionAccessors(providers, config);
        public IoGithubVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.github.bonigarcia
         */
        public IoGithubBonigarciaVersionAccessors getBonigarcia() {
            return vaccForIoGithubBonigarciaVersionAccessors;
        }

    }

    public static class IoGithubBonigarciaVersionAccessors extends VersionFactory  {

        public IoGithubBonigarciaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: io.github.bonigarcia.webdrivermanager (5.6.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getWebdrivermanager() { return getVersion("io.github.bonigarcia.webdrivermanager"); }

    }

    public static class JunitVersionAccessors extends VersionFactory  {

        public JunitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: junit.junit (4.13.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit.junit"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgAssertjVersionAccessors vaccForOrgAssertjVersionAccessors = new OrgAssertjVersionAccessors(providers, config);
        private final OrgSeleniumhqVersionAccessors vaccForOrgSeleniumhqVersionAccessors = new OrgSeleniumhqVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.assertj
         */
        public OrgAssertjVersionAccessors getAssertj() {
            return vaccForOrgAssertjVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.seleniumhq
         */
        public OrgSeleniumhqVersionAccessors getSeleniumhq() {
            return vaccForOrgSeleniumhqVersionAccessors;
        }

    }

    public static class OrgAssertjVersionAccessors extends VersionFactory  {

        private final OrgAssertjAssertjVersionAccessors vaccForOrgAssertjAssertjVersionAccessors = new OrgAssertjAssertjVersionAccessors(providers, config);
        public OrgAssertjVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.assertj.assertj
         */
        public OrgAssertjAssertjVersionAccessors getAssertj() {
            return vaccForOrgAssertjAssertjVersionAccessors;
        }

    }

    public static class OrgAssertjAssertjVersionAccessors extends VersionFactory  {

        public OrgAssertjAssertjVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.assertj.assertj.core (3.24.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("org.assertj.assertj.core"); }

    }

    public static class OrgSeleniumhqVersionAccessors extends VersionFactory  {

        private final OrgSeleniumhqSeleniumVersionAccessors vaccForOrgSeleniumhqSeleniumVersionAccessors = new OrgSeleniumhqSeleniumVersionAccessors(providers, config);
        public OrgSeleniumhqVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.seleniumhq.selenium
         */
        public OrgSeleniumhqSeleniumVersionAccessors getSelenium() {
            return vaccForOrgSeleniumhqSeleniumVersionAccessors;
        }

    }

    public static class OrgSeleniumhqSeleniumVersionAccessors extends VersionFactory  {

        private final OrgSeleniumhqSeleniumSeleniumVersionAccessors vaccForOrgSeleniumhqSeleniumSeleniumVersionAccessors = new OrgSeleniumhqSeleniumSeleniumVersionAccessors(providers, config);
        public OrgSeleniumhqSeleniumVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.seleniumhq.selenium.selenium
         */
        public OrgSeleniumhqSeleniumSeleniumVersionAccessors getSelenium() {
            return vaccForOrgSeleniumhqSeleniumSeleniumVersionAccessors;
        }

    }

    public static class OrgSeleniumhqSeleniumSeleniumVersionAccessors extends VersionFactory  {

        public OrgSeleniumhqSeleniumSeleniumVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.seleniumhq.selenium.selenium.java (4.15.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJava() { return getVersion("org.seleniumhq.selenium.selenium.java"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
