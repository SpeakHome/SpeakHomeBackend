package com.ExcludedHouse.speakHome.shared.configuration;

import com.ExcludedHouse.speakHome.deviceIot.domain.model.DeviceStatus;
import com.ExcludedHouse.speakHome.deviceIot.domain.model.Location;
import com.ExcludedHouse.speakHome.deviceIot.domain.persistence.DeviceStatusRepository;
import com.ExcludedHouse.speakHome.deviceIot.domain.persistence.LocationRepository;
import com.ExcludedHouse.speakHome.security.domain.model.Role;
import com.ExcludedHouse.speakHome.security.domain.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataBaseLoader implements CommandLineRunner {


    private final RoleRepository roleRepository;
    private final LocationRepository locationRepository;
    private final DeviceStatusRepository deviceStatusRepository;

    @Autowired
    public DataBaseLoader(RoleRepository roleRepository, LocationRepository locationRepository, DeviceStatusRepository deviceStatusRepository) {
        this.roleRepository = roleRepository;
        this.locationRepository = locationRepository;
        this.deviceStatusRepository = deviceStatusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Carga inicial de roles
        loadRoles();
        // Carga inicial de ubicaciones
        loadLocations();
        // Carga inicial de estados de dispositivos
        loadDeviceStatuses();
    }

    private void loadRoles() {
        if (roleRepository.findByName("tecnico").isEmpty()) {
            roleRepository.save(new Role(null, "tecnico", new HashSet<>()));
        }
        if (roleRepository.findByName("noTecnico").isEmpty()) {
            roleRepository.save(new Role(null, "noTecnico", new HashSet<>()));
        }
    }

    private void loadLocations() {
        if (locationRepository.count() == 0) { // Asumiendo que hay un método count()
            locationRepository.save(new Location(null, "Vestíbulo", new HashSet<>()));
            locationRepository.save(new Location(null, "Sala de Estar", new HashSet<>()));
            locationRepository.save(new Location(null, "Comedor", new HashSet<>()));
            locationRepository.save(new Location(null, "Cocina", new HashSet<>()));

            locationRepository.save(new Location(null, "Dormitorio Principal", new HashSet<>()));
            locationRepository.save(new Location(null, "Dormitorio de Invitados", new HashSet<>()));
            locationRepository.save(new Location(null, "Baño Principal", new HashSet<>()));
            locationRepository.save(new Location(null, "Baño Secundario", new HashSet<>()));

            locationRepository.save(new Location(null, "Estudio", new HashSet<>()));
            locationRepository.save(new Location(null, "Sala de Juegos", new HashSet<>()));
            locationRepository.save(new Location(null, "Gimnasio", new HashSet<>()));
            locationRepository.save(new Location(null, "Lavandería", new HashSet<>()));

            locationRepository.save(new Location(null, "Terraza", new HashSet<>()));
            locationRepository.save(new Location(null, "Patio", new HashSet<>()));
            locationRepository.save(new Location(null, "Jardín", new HashSet<>()));
            locationRepository.save(new Location(null, "Garaje", new HashSet<>()));

            locationRepository.save(new Location(null, "Sótano", new HashSet<>()));
            locationRepository.save(new Location(null, "Ático", new HashSet<>()));
            // Puedes continuar agregando más ubicaciones si es necesario
        }
    }

    private void loadDeviceStatuses() {
        if (deviceStatusRepository.count() == 0) { // Asumiendo que hay un método count()
            deviceStatusRepository.save(new DeviceStatus(null, "Operativo", new HashSet<>()));
            deviceStatusRepository.save(new DeviceStatus(null, "En Mantenimiento", new HashSet<>()));
            deviceStatusRepository.save(new DeviceStatus(null, "Desconectado", new HashSet<>()));
            // ... más estados si es necesario
        }
    }
}