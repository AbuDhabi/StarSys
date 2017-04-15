/*
 * Copyright (C) 2017 abudhabi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package starsys.generator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import starsys.model.Atmosphere;
import starsys.model.Chunk;
import starsys.model.GasGiant;
import starsys.model.MassiveBody;
import starsys.model.OrbitalPoint;
import starsys.model.Star;
import starsys.model.Terrestrial;
import starsys.util.ChunkClass;
import starsys.util.Constants;
import starsys.util.GasGiantClass;
import starsys.util.SpectralClass;
import starsys.util.TerrestrialClass;

/**
 *
 * @author abudhabi
 */
public class DefaultGenerator implements Generator {
    private final Random random;
    private long currentId;

    public DefaultGenerator(long seed) {
        this.random = new Random(seed);
        currentId = 0;
    }

    @Override
    public Star generateStar(CelestialBodyParameters params) {
        long id = currentId++;
        CelestialBodyParameters builder = new CelestialBodyParameters();
        if (params.getSpectralClass() == null) {
            builder.setSpectralClass(SpectralClass.getRandomSpectralClassOToM(random));
        } else {
            builder.setSpectralClass(params.getSpectralClass());
        }
        
        if (params.getName() == null) {
            builder.setName(builder.getSpectralClass().name()+"-class Star ("+id+")");
        } else {
            builder.setName(params.getName());
        }
        
        if (params.getTemperature() == null) {
            builder.setTemperature(
                    random.doubles(
                    builder.getSpectralClass().getLowerTemperature(),
                    builder.getSpectralClass().getUpperTemperature())
                    .findFirst().getAsDouble()
            );
        } else {
            builder.setTemperature(params.getTemperature());
        }
        if (params.getMass() == null) {
            builder.setMass(random.doubles(
                    builder.getSpectralClass().getLowerMass(), 
                    builder.getSpectralClass().getUpperMass())
                    .findFirst().getAsDouble()
            );
        } else {
            builder.setMass(params.getMass());
        }
        if (params.getRadius() == null) {
            builder.setRadius(random.doubles(
                    builder.getSpectralClass().getLowerRadius(), 
                    builder.getSpectralClass().getUpperRadius())
                    .findFirst().getAsDouble()
            );
        } else {
            builder.setRadius(params.getRadius());
        }
        
        // Primary, or companion?
        Point2D.Double center; 
        double semiMajorAxis;
        double angularVelocity;
        if (params.getParent() == null) {
            // Primary.
            center = new Point2D.Double(); // 0,0
            semiMajorAxis = 0;
            angularVelocity = 0;
            builder.setParent(null);
        } else {
            // Companion.
            builder.setParent(params.getParent());
            center = null;
            double lowerBound;
            double upperBound;
            boolean nearCompanion;
            if (params.getNearCompanion() == null) {
                nearCompanion = random.nextBoolean();
            } else {
                nearCompanion = params.getNearCompanion();
            }
            if (nearCompanion) {
                // Near companion. Up to 100 AU from the primary.
                upperBound = 100 * Constants.ASTRONOMICAL_UNIT;
                if (params.getParent() instanceof MassiveBody) {
                    // Meaning it has a radius, which the child should stay away from.
                    lowerBound = 2* ((MassiveBody)params.getParent()).getRadius();
                } else {
                    lowerBound = Constants.ASTRONOMICAL_UNIT;
                }
            } else {
                // Far companion. More than 100 AU from the primary.
                lowerBound = Constants.ASTRONOMICAL_UNIT;
                upperBound = 1000*Constants.ASTRONOMICAL_UNIT;
            }
            semiMajorAxis = lowerBound + random.nextDouble()*(upperBound-lowerBound);
            // Many hours died to bring us this solution.
            double parentMass;
            if (params.getParent() instanceof MassiveBody) {
                parentMass = ((MassiveBody)params.getParent()).getMass();
            } else {
                parentMass = Constants.SUN_MASS; // Placeholder, but useful. 
            }
            double mi = Constants.GRAVITATIONAL_CONSTANT*(builder.getMass() + parentMass); // In km^3/day^2.
            double orbitalPeriod = 2*Math.PI*Math.sqrt(Math.pow(semiMajorAxis, 3)/mi); // In days.
            angularVelocity = 2*Math.PI/orbitalPeriod; // In radians per day.
        }
        if (params.getCenter() == null) {
            builder.setCenter(center);
        } else {
            builder.setCenter(params.getCenter());
        }
        if (params.getSemiMajorAxis() == null) {
            builder.setSemiMajorAxis(semiMajorAxis);
        } else {
            builder.setSemiMajorAxis(params.getSemiMajorAxis());
        }
        if (params.getAngularVelocity() == null) {
            builder.setAngularVelocity(angularVelocity);
        } else {
            builder.setAngularVelocity(params.getAngularVelocity());
        }
        if (params.getEccentricity() == null) {
            builder.setEccentricity(0.0);
        } else {
            builder.setEccentricity(params.getEccentricity());
        }
        if (params.getInclination() == null) {
            builder.setInclination(0.0);
        } else {
            builder.setInclination(params.getInclination());
        }
        if (params.getTilt() == null) {
            builder.setTilt(0.0);
        } else {
            builder.setTilt(params.getTilt());
        }
        if (params.getRotationVelocity() == null) {
            builder.setRotationVelocity(0.0);
        } else {
            builder.setRotationVelocity(params.getRotationVelocity());
        }
        if (params.getAlbedo() == null) {
            builder.setAlbedo(0.0); // A star is probably a good blackbody.
        } else {
            builder.setAlbedo(params.getAlbedo());
        }
        if (params.getCachedTime() == null) {
            builder.setCachedTime(0.0);
        } else {
            builder.setCachedTime(params.getCachedTime());
        }
        if (params.getOffset()== null) {
            builder.setOffset(random.nextInt());
        } else {
            builder.setOffset(params.getOffset());
        }
        if (params.getChildren() == null) {
            builder.setChildren(new ArrayList<>());
        } else {
            builder.setChildren(params.getChildren());
        }
        
        return new Star(
                builder.getSpectralClass(),
                builder.getTilt(),
                builder.getRotationVelocity(),
                builder.getMass(),
                builder.getRadius(), 
                builder.getTemperature(),
                builder.getAlbedo(), 
                builder.getSpectralClass().getColor(),
                id,
                builder.getName(), 
                builder.getCachedTime(), 
                builder.getOffset(),
                builder.getCenter(), 
                builder.getParent(), 
                builder.getChildren(),
                builder.getSemiMajorAxis(),
                builder.getAngularVelocity(), 
                builder.getEccentricity(), 
                builder.getInclination());
    }

    @Override
    public Terrestrial generateTerrestrialWorld(CelestialBodyParameters params) {
        long id = currentId++;
        CelestialBodyParameters builder = new CelestialBodyParameters();
        
        if (params.getTerrestrialClass()== null) {
            builder.setTerrestrialClass(TerrestrialClass.getRandomTerrestrialClass(random));
        } else {
            builder.setTerrestrialClass(params.getTerrestrialClass());
        }
        
        if (params.getName() == null) {
            builder.setName(builder.getTerrestrialClass().name()+" world ("+id+")");
        } else {
            builder.setName(params.getName());
        }
        
        if (params.getTemperature()== null) {
            builder.setTemperature(0.0);
        } else {
            builder.setTemperature(params.getTemperature());
        }
        
        if (params.getMass() == null) {
            builder.setMass(random.doubles(
                    builder.getTerrestrialClass().getLowerMass(), 
                    builder.getTerrestrialClass().getUpperMass())
                    .findFirst().getAsDouble()
            );
        } else {
            builder.setMass(params.getMass());
        }
        double density;
        if (params.getDensity() == null) {
            density = random.doubles(
                builder.getTerrestrialClass().getLowerDensity(), 
                builder.getTerrestrialClass().getUpperDensity())
                .findFirst().getAsDouble();
        } else {
            density = params.getDensity();
        }
        builder.setRadius(Math.cbrt((builder.getMass())/(4/3*Math.PI*density)));
        
        // Might be a rogue planet in the middle of nowhere.
        Point2D.Double center; 
        double angularVelocity;
        if (params.getParent() == null) {
            center = new Point2D.Double(); // 0,0
            builder.setSemiMajorAxis(0.0);
            angularVelocity = 0;
            builder.setParent(null);
        } else {
            builder.setParent(params.getParent());
            center = null;
            if (params.getSemiMajorAxis() == null) {
                double lowerBound = Constants.ASTRONOMICAL_UNIT;
                double upperBound = 60*Constants.ASTRONOMICAL_UNIT;
                builder.setSemiMajorAxis(lowerBound + random.nextDouble()*(upperBound-lowerBound));
            } else {
                builder.setSemiMajorAxis(params.getSemiMajorAxis());
            }
            
            // Many hours died to bring us this solution.
            double parentMass;
            if (params.getParent() instanceof MassiveBody) {
                parentMass = ((MassiveBody)params.getParent()).getMass();
            } else {
                parentMass = Constants.SUN_MASS; // Placeholder, but useful. 
            }
            double mi = Constants.GRAVITATIONAL_CONSTANT*(builder.getMass() + parentMass); // In km^3/day^2.
            double orbitalPeriod = 2*Math.PI*Math.sqrt(Math.pow(builder.getSemiMajorAxis(), 3)/mi); // In days.
            angularVelocity = 2*Math.PI/orbitalPeriod; // In radians per day.
        }
        if (params.getCenter() == null) {
            builder.setCenter(center);
        } else {
            builder.setCenter(params.getCenter());
        }
        if (params.getAngularVelocity() == null) {
            builder.setAngularVelocity(angularVelocity);
        } else {
            builder.setAngularVelocity(params.getAngularVelocity());
        }
        if (params.getEccentricity() == null) {
            builder.setEccentricity(0.0);
        } else {
            builder.setEccentricity(params.getEccentricity());
        }
        if (params.getInclination() == null) {
            builder.setInclination(0.0);
        } else {
            builder.setInclination(params.getInclination());
        }
        if (params.getTilt() == null) {
            builder.setTilt(0.0);
        } else {
            builder.setTilt(params.getTilt());
        }
        if (params.getRotationVelocity() == null) {
            builder.setRotationVelocity(0.0);
        } else {
            builder.setRotationVelocity(params.getRotationVelocity());
        }
        if (params.getAlbedo() == null) {
            builder.setAlbedo(0.1 + random.nextDouble()*0.8); 
        } else {
            builder.setAlbedo(params.getAlbedo());
        }
        if (params.getCachedTime() == null) {
            builder.setCachedTime(0.0);
        } else {
            builder.setCachedTime(params.getCachedTime());
        }
        if (params.getOffset()== null) {
            builder.setOffset(random.nextInt());
        } else {
            builder.setOffset(params.getOffset());
        }
        if (params.getChildren() == null) {
            builder.setChildren(new ArrayList<>());
        } else {
            builder.setChildren(params.getChildren());
        }
        if (params.getAtmosphere() == null) {
            builder.setAtmosphere(new Atmosphere());
        } else {
            builder.setAtmosphere(params.getAtmosphere());
        }
        
        return new Terrestrial(
                builder.getAtmosphere(),
                builder.getTerrestrialClass(),
                builder.getTilt(),
                builder.getRotationVelocity(),
                builder.getMass(),
                builder.getRadius(),
                builder.getTemperature(),
                builder.getAlbedo(),
                builder.getTerrestrialClass().getColor(),
                id,
                builder.getName(),
                builder.getCachedTime(),
                builder.getOffset(),
                builder.getCenter(),
                builder.getParent(),
                builder.getChildren(),
                builder.getSemiMajorAxis(),
                builder.getAngularVelocity(),
                builder.getEccentricity(),
                builder.getInclination()
                );
    }

    @Override
    public Chunk generateChunk(CelestialBodyParameters params) {
        long id = currentId++;
        CelestialBodyParameters builder = new CelestialBodyParameters();
        
        if (params.getChunkClass()== null) {
            builder.setChunkClass(ChunkClass.getRandomChunkClass(random));
        } else {
            builder.setChunkClass(params.getChunkClass());
        }
        
        if (params.getName() == null) {
            builder.setName(builder.getChunkClass().name()+"-class Asteroid ("+id+")");
        } else {
            builder.setName(params.getName());
        }
        
        if (params.getTemperature()== null) {
            builder.setTemperature(0.0);
        } else {
            builder.setTemperature(params.getTemperature());
        }
        
        if (params.getMass() == null) {
            builder.setMass(random.doubles(
                    builder.getChunkClass().getLowerMass(), 
                    builder.getChunkClass().getUpperMass())
                    .findFirst().getAsDouble()
            );
        } else {
            builder.setMass(params.getMass());
        }
        double density;
        if (params.getDensity() == null) {
            density = random.doubles(
                builder.getChunkClass().getLowerDensity(), 
                builder.getChunkClass().getUpperDensity())
                .findFirst().getAsDouble();
        } else {
            density = params.getDensity();
        }
        builder.setRadius(Math.cbrt((builder.getMass())/(4/3*Math.PI*density)));
        
        // Might be a rogue planet in the middle of nowhere.
        Point2D.Double center; 
        double angularVelocity;
        if (params.getParent() == null) {
            center = new Point2D.Double(); // 0,0
            builder.setSemiMajorAxis(0.0);
            angularVelocity = 0;
            builder.setParent(null);
        } else {
            builder.setParent(params.getParent());
            center = null;
            if (params.getSemiMajorAxis() == null) {
                double lowerBound = Constants.ASTRONOMICAL_UNIT;
                double upperBound = 60*Constants.ASTRONOMICAL_UNIT;
                builder.setSemiMajorAxis(lowerBound + random.nextDouble()*(upperBound-lowerBound));
            } else {
                builder.setSemiMajorAxis(params.getSemiMajorAxis());
            }
            
            // Many hours died to bring us this solution.
            double parentMass;
            if (params.getParent() instanceof MassiveBody) {
                parentMass = ((MassiveBody)params.getParent()).getMass();
            } else {
                parentMass = Constants.SUN_MASS; // Placeholder, but useful. 
            }
            double mi = Constants.GRAVITATIONAL_CONSTANT*(builder.getMass() + parentMass); // In km^3/day^2.
            double orbitalPeriod = 2*Math.PI*Math.sqrt(Math.pow(builder.getSemiMajorAxis(), 3)/mi); // In days.
            angularVelocity = 2*Math.PI/orbitalPeriod; // In radians per day.
        }
        if (params.getCenter() == null) {
            builder.setCenter(center);
        } else {
            builder.setCenter(params.getCenter());
        }
        if (params.getAngularVelocity() == null) {
            builder.setAngularVelocity(angularVelocity);
        } else {
            builder.setAngularVelocity(params.getAngularVelocity());
        }
        if (params.getEccentricity() == null) {
            builder.setEccentricity(0.0);
        } else {
            builder.setEccentricity(params.getEccentricity());
        }
        if (params.getInclination() == null) {
            builder.setInclination(0.0);
        } else {
            builder.setInclination(params.getInclination());
        }
        if (params.getTilt() == null) {
            builder.setTilt(0.0);
        } else {
            builder.setTilt(params.getTilt());
        }
        if (params.getRotationVelocity() == null) {
            builder.setRotationVelocity(0.0);
        } else {
            builder.setRotationVelocity(params.getRotationVelocity());
        }
        if (params.getAlbedo() == null) {
            double baseAlbedo = builder.getChunkClass().getBaseAlbedo();
            builder.setAlbedo(baseAlbedo + random.nextDouble()*(builder.getChunkClass().getBaseAlbedo()*2)); 
        } else {
            builder.setAlbedo(params.getAlbedo());
        }
        if (params.getCachedTime() == null) {
            builder.setCachedTime(0.0);
        } else {
            builder.setCachedTime(params.getCachedTime());
        }
        if (params.getOffset()== null) {
            builder.setOffset(random.nextInt());
        } else {
            builder.setOffset(params.getOffset());
        }
        if (params.getChildren() == null) {
            builder.setChildren(new ArrayList<>());
        } else {
            builder.setChildren(params.getChildren());
        }
        
        return new Chunk(
                builder.getChunkClass(),
                builder.getTilt(),
                builder.getRotationVelocity(),
                builder.getMass(),
                builder.getRadius(),
                builder.getTemperature(),
                builder.getAlbedo(),
                builder.getChunkClass().getColor(),
                id,
                builder.getName(),
                builder.getCachedTime(),
                builder.getOffset(),
                builder.getCenter(),
                builder.getParent(),
                builder.getChildren(),
                builder.getSemiMajorAxis(),
                builder.getAngularVelocity(),
                builder.getEccentricity(),
                builder.getInclination()
                );
    }

    @Override
    public GasGiant generateGasGiant(CelestialBodyParameters params) {
        long id = currentId++;
        CelestialBodyParameters builder = new CelestialBodyParameters();
        
        if (params.getGasGiantClass()== null) {
            builder.setGasGiantClass(GasGiantClass.getRandomGasGiantClass(random));
        } else {
            builder.setGasGiantClass(params.getGasGiantClass());
        }
        
        if (params.getName() == null) {
            builder.setName(builder.getGasGiantClass().name()+"-class Gas Giant ("+id+")");
        } else {
            builder.setName(params.getName());
        }
        
        if (params.getTemperature()== null) {
            builder.setTemperature(0.0);
        } else {
            builder.setTemperature(params.getTemperature());
        }
        
        if (params.getMass() == null) {
            builder.setMass(random.doubles(
                    builder.getGasGiantClass().getLowerMass(), 
                    builder.getGasGiantClass().getUpperMass())
                    .findFirst().getAsDouble()
            );
        } else {
            builder.setMass(params.getMass());
        }
        double density;
        if (params.getDensity() == null) {
            density = random.doubles(
                builder.getGasGiantClass().getLowerDensity(), 
                builder.getGasGiantClass().getUpperDensity())
                .findFirst().getAsDouble();
        } else {
            density = params.getDensity();
        }
        builder.setRadius(Math.cbrt((builder.getMass())/(4/3*Math.PI*density)));
        
        // Might be a rogue planet in the middle of nowhere.
        Point2D.Double center; 
        double angularVelocity;
        if (params.getParent() == null) {
            center = new Point2D.Double(); // 0,0
            builder.setSemiMajorAxis(0.0);
            angularVelocity = 0;
            builder.setParent(null);
        } else {
            builder.setParent(params.getParent());
            center = null;
            if (params.getSemiMajorAxis() == null) {
                double lowerBound = Constants.ASTRONOMICAL_UNIT;
                double upperBound = 60*Constants.ASTRONOMICAL_UNIT;
                builder.setSemiMajorAxis(lowerBound + random.nextDouble()*(upperBound-lowerBound));
            } else {
                builder.setSemiMajorAxis(params.getSemiMajorAxis());
            }
            
            // Many hours died to bring us this solution.
            double parentMass;
            if (params.getParent() instanceof MassiveBody) {
                parentMass = ((MassiveBody)params.getParent()).getMass();
            } else {
                parentMass = Constants.SUN_MASS; // Placeholder, but useful. 
            }
            double mi = Constants.GRAVITATIONAL_CONSTANT*(builder.getMass() + parentMass); // In km^3/day^2.
            double orbitalPeriod = 2*Math.PI*Math.sqrt(Math.pow(builder.getSemiMajorAxis(), 3)/mi); // In days.
            angularVelocity = 2*Math.PI/orbitalPeriod; // In radians per day.
        }
        if (params.getCenter() == null) {
            builder.setCenter(center);
        } else {
            builder.setCenter(params.getCenter());
        }
        if (params.getAngularVelocity() == null) {
            builder.setAngularVelocity(angularVelocity);
        } else {
            builder.setAngularVelocity(params.getAngularVelocity());
        }
        if (params.getEccentricity() == null) {
            builder.setEccentricity(0.0);
        } else {
            builder.setEccentricity(params.getEccentricity());
        }
        if (params.getInclination() == null) {
            builder.setInclination(0.0);
        } else {
            builder.setInclination(params.getInclination());
        }
        if (params.getTilt() == null) {
            builder.setTilt(0.0);
        } else {
            builder.setTilt(params.getTilt());
        }
        if (params.getRotationVelocity() == null) {
            builder.setRotationVelocity(0.0);
        } else {
            builder.setRotationVelocity(params.getRotationVelocity());
        }
        if (params.getAlbedo() == null) {
            builder.setAlbedo(0.1 + random.nextDouble()*0.8); 
        } else {
            builder.setAlbedo(params.getAlbedo());
        }
        if (params.getCachedTime() == null) {
            builder.setCachedTime(0.0);
        } else {
            builder.setCachedTime(params.getCachedTime());
        }
        if (params.getOffset()== null) {
            builder.setOffset(random.nextInt());
        } else {
            builder.setOffset(params.getOffset());
        }
        if (params.getChildren() == null) {
            builder.setChildren(new ArrayList<>());
        } else {
            builder.setChildren(params.getChildren());
        }
        if (params.getAtmosphere() == null) {
            builder.setAtmosphere(new Atmosphere());
        } else {
            builder.setAtmosphere(params.getAtmosphere());
        }
        
        return new GasGiant(
                builder.getAtmosphere(),
                builder.getGasGiantClass(),
                builder.getTilt(),
                builder.getRotationVelocity(),
                builder.getMass(),
                builder.getRadius(),
                builder.getTemperature(),
                builder.getAlbedo(),
                builder.getGasGiantClass().getColor(),
                id,
                builder.getName(),
                builder.getCachedTime(),
                builder.getOffset(),
                builder.getCenter(),
                builder.getParent(),
                builder.getChildren(),
                builder.getSemiMajorAxis(),
                builder.getAngularVelocity(),
                builder.getEccentricity(),
                builder.getInclination()
                );
    }

    @Override
    public OrbitalPoint generateSystem(SystemParameters params) {
        if (params.getParent() == null) {
            // This is the root node, typically a star.
            Star primary = generateStar(params.getSpecification());
            if (params.isChildrenAllowed()) {
                params.getChildren().stream().forEach((p) -> {
                    primary.getChildren().add(generateSystem(p));
                });
            }
            return primary;
        } else {
            // This node is somewhere in the bowels of the system, not the root.
            // Could be a star, but could be a planet or asteroid.
            if (params.getSpecification().getSpectralClass() != null) {
                Star companion = generateStar(params.getSpecification());
                if (params.isChildrenAllowed()) {
                    params.getChildren().stream().forEach((p) -> {
                        companion.getChildren().add(generateSystem(p));
                    });
                }
                return companion;
            } else if (params.getSpecification().getGasGiantClass() != null) {
                GasGiant giant = generateGasGiant(params.getSpecification());
                if (params.isChildrenAllowed()) {
                    params.getChildren().stream().forEach((p) -> {
                        giant.getChildren().add(generateSystem(p));
                    });
                }
                return giant;
            } else if (params.getSpecification().getTerrestrialClass() != null) {
                Terrestrial rock = generateTerrestrialWorld(params.getSpecification());
                if (params.isChildrenAllowed()) {
                    params.getChildren().stream().forEach((p) -> {
                        rock.getChildren().add(generateSystem(p));
                    });
                }
                return rock;
            } else if (params.getSpecification().getChunkClass() != null) {
                Chunk chunk = generateChunk(params.getSpecification());
                if (params.isChildrenAllowed()) {
                    params.getChildren().stream().forEach((p) -> {
                        chunk.getChildren().add(generateSystem(p));
                    });
                }
                return chunk;
            } else {
                // OK, I'll choose which makes sense.
                if (params.getParent().getSpecification().getSpectralClass() != null) {
                    // Parent is a star. Options for this child:
                    // - Smaller star.
                    // - Gas giant.
                    // - Rocky world.
                    // - Chunk.
                    int number = random.nextInt(10);
                    if (number == 0) {
                        Star companion = generateStar(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                companion.getChildren().add(generateSystem(p));
                            });
                        }
                        return companion;
                    } else if (number > 0 && number <= 3) {
                        GasGiant giant = generateGasGiant(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                giant.getChildren().add(generateSystem(p));
                            });
                        }
                        return giant;
                    } else if (number > 3 && number <= 6) {
                        Terrestrial rock = generateTerrestrialWorld(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                rock.getChildren().add(generateSystem(p));
                            });
                        }
                        return rock;
                    } else {
                        Chunk chunk = generateChunk(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                chunk.getChildren().add(generateSystem(p));
                            });
                        }
                        return chunk;
                    }
                } else if (params.getParent().getSpecification().getGasGiantClass() != null) {
                    // Parent is a gas giant. Options for this child:
                    // - Rocky world.
                    // - Chunk.
                    if (random.nextBoolean()) {
                        Terrestrial rock = generateTerrestrialWorld(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                rock.getChildren().add(generateSystem(p));
                            });
                        }
                        return rock;
                    } else {
                        Chunk chunk = generateChunk(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                chunk.getChildren().add(generateSystem(p));
                            });
                        }
                        return chunk;
                    }
                } else if (params.getParent().getSpecification().getTerrestrialClass() != null) {
                    // Parent is a rocky world. Options for this child:
                    // - Smaller rocky world. Not implemented to make sure it's smaller.
                    // - Chunk.
                    if (random.nextBoolean()) {
                        Terrestrial rock = generateTerrestrialWorld(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                rock.getChildren().add(generateSystem(p));
                            });
                        }
                        return rock;
                    } else {
                        Chunk chunk = generateChunk(params.getSpecification());
                        if (params.isChildrenAllowed()) {
                            params.getChildren().stream().forEach((p) -> {
                                chunk.getChildren().add(generateSystem(p));
                            });
                        }
                        return chunk;
                    }
                } else if (params.getParent().getSpecification().getChunkClass() != null) {
                    // Parent is a chunk. Options for this child:
                    // - Smaller chunk. Not actually implemented to make sure it's smaller!
                    Chunk chunk = generateChunk(params.getSpecification());
                    if (params.isChildrenAllowed()) {
                        params.getChildren().stream().forEach((p) -> {
                            chunk.getChildren().add(generateSystem(p));
                        });
                    }
                    return chunk;
                } else {
                    // OK, both this child and the parent are unspecified.
                    // Safest decision is to make a chunk. 
                    // Which still doesn't guarantee that stuff will make sense.
                    Chunk chunk = generateChunk(params.getSpecification());
                    if (params.isChildrenAllowed()) {
                        params.getChildren().stream().forEach((p) -> {
                            chunk.getChildren().add(generateSystem(p));
                        });
                    }
                    return chunk;
                }

            }
            
        }
    }

    @Override
    public List<Chunk> generateBelt(CelestialBodyParameters params, int nrofChunks) {
        List<Chunk> belt = new ArrayList<>();
        for (int i=0; i<nrofChunks; i++) {
            belt.add(generateChunk(params));
        }
        return belt;
    }

    
}
