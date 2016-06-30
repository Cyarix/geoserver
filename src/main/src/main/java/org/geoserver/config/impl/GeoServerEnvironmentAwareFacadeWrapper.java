/* (c) 2016 Open Source Geospatial Foundation - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.config.GeoServer;
import org.geoserver.config.GeoServerFacade;
import org.geoserver.config.GeoServerInfo;
import org.geoserver.config.LoggingInfo;
import org.geoserver.config.ServiceInfo;
import org.geoserver.config.SettingsInfo;
import org.geoserver.ows.Dispatcher;
import org.geoserver.ows.Request;
import org.geoserver.platform.GeoServerEnvironment;

/**
 * @author Alessio Fabiani, GeoSolutions
 *
 */
public class GeoServerEnvironmentAwareFacadeWrapper implements GeoServerFacade {

    /**
     * data access object
     */
    GeoServerFacade wrapped;
    
    /**
     * 
     */
    public GeoServerEnvironmentAwareFacadeWrapper(GeoServerFacade facade) {
        this.wrapped = facade;
    }

    @Override
    public GeoServer getGeoServer() {
        return wrapped.getGeoServer();
    }

    @Override
    public void setGeoServer(GeoServer geoServer) {
        wrapped.setGeoServer(geoServer);
    }

    @Override
    public GeoServerInfo getGlobal() {
        return wrapped.getGlobal();
    }

    @Override
    public void setGlobal(GeoServerInfo global) {
        wrapped.setGlobal(global);
    }

    @Override
    public void save(GeoServerInfo geoServer) {
        wrapped.save(geoServer);
    }

    @Override
    public SettingsInfo getSettings(WorkspaceInfo workspace) {
        return wrapped.getSettings(workspace);
    }

    @Override
    public void add(SettingsInfo settings) {
        wrapped.add(settings);
    }

    @Override
    public void save(SettingsInfo settings) {
        wrapped.save(settings);
    }

    @Override
    public void remove(SettingsInfo settings) {
        wrapped.remove(settings);
    }

    @Override
    public LoggingInfo getLogging() {
        return wrapped.getLogging();
    }

    @Override
    public void setLogging(LoggingInfo logging) {
        wrapped.setLogging(logging);
    }

    @Override
    public void save(LoggingInfo logging) {
        wrapped.save(logging);
    }

    @Override
    public void add(ServiceInfo service) {
        wrapped.add(service);
    }

    @Override
    public void remove(ServiceInfo service) {
        wrapped.remove(service);
    }

    @Override
    public void save(ServiceInfo service) {
        wrapped.save(service);
    }

    @Override
    public Collection<? extends ServiceInfo> getServices() {
        Collection wrappedServices = new ArrayList();
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION) {
            // It is an OWS request; the placeholders must be expanded
            Iterator<? extends ServiceInfo> it = wrapped.getServices().iterator();
            while(it.hasNext()) {
                ServiceInfo service = it.next();
                if (service != null) {
                    wrappedServices.add(service.clone(true));
                }
            }
        } else {
            wrappedServices = wrapped.getServices();
        }
        
        return wrappedServices;
    }

    @Override
    public Collection<? extends ServiceInfo> getServices(WorkspaceInfo workspace) {
        Collection wrappedServices = new ArrayList();
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION) {
            // It is an OWS request; the placeholders must be expanded
            Iterator<? extends ServiceInfo> it = wrapped.getServices(workspace).iterator();
            while(it.hasNext()) {
                ServiceInfo service = it.next();
                if (service != null) {
                    wrappedServices.add(service.clone(true));
                }
            }
        } else {
            wrappedServices = wrapped.getServices(workspace);
        }
        
        return wrappedServices;
    }

    @Override
    public <T extends ServiceInfo> T getService(Class<T> clazz) {
        T wrappedService = null;
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION &&
                wrapped.getService(clazz) != null) {
            // It is an OWS request; the placeholders must be expanded
            wrappedService = (T) wrapped.getService(clazz).clone(true);
        } else {
            wrappedService = wrapped.getService(clazz);
        }
        
        return wrappedService;
    }

    @Override
    public <T extends ServiceInfo> T getService(WorkspaceInfo workspace, Class<T> clazz) {
        T wrappedService = null;
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION &&
                wrapped.getService(workspace, clazz) != null) {
            // It is an OWS request; the placeholders must be expanded
            wrappedService = (T) wrapped.getService(workspace, clazz).clone(true);
        } else {
            wrappedService = wrapped.getService(workspace, clazz);
        }
        
        return wrappedService;
    }

    @Override
    public <T extends ServiceInfo> T getService(String id, Class<T> clazz) {
        T wrappedService = null;
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION &&
                wrapped.getService(id, clazz) != null) {
            // It is an OWS request; the placeholders must be expanded
            wrappedService = (T) wrapped.getService(id, clazz).clone(true);
        } else {
            wrappedService = wrapped.getService(id, clazz);
        }
        
        return wrappedService;
    }

    @Override
    public <T extends ServiceInfo> T getServiceByName(String name, Class<T> clazz) {
        T wrappedService = null;
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION &&
                wrapped.getServiceByName(name, clazz) != null) {
            // It is an OWS request; the placeholders must be expanded
            wrappedService = (T) wrapped.getServiceByName(name, clazz).clone(true);
        } else {
            wrappedService =  wrapped.getServiceByName(name, clazz);
        }
        
        return wrappedService;
    }

    @Override
    public <T extends ServiceInfo> T getServiceByName(String name, WorkspaceInfo workspace,
            Class<T> clazz) {
        T wrappedService = null;
        Request request = Dispatcher.REQUEST.get();
        if (request != null && GeoServerEnvironment.ALLOW_ENV_PARAMETRIZATION &&
                wrapped.getServiceByName(name, workspace, clazz) != null) {
            // It is an OWS request; the placeholders must be expanded
            wrappedService = (T) wrapped.getServiceByName(name, workspace, clazz).clone(true);
        } else {
            wrappedService =  wrapped.getServiceByName(name, workspace, clazz);
        }
        
        return wrappedService;
    }

    @Override
    public void dispose() {
        wrapped.dispose();
    }

}
