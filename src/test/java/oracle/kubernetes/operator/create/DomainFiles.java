// Copyright 2018, Oracle Corporation and/or its affiliates.  All rights reserved.
// Licensed under the Universal Permissive License v 1.0 as shown at http://oss.oracle.com/licenses/upl.

package oracle.kubernetes.operator.create;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the input and generated files for a domain
 */
public class DomainFiles {

  public static final String CREATE_SCRIPT = "src/test/scripts/unit-test-create-weblogic-domain.sh";
  private static final String CREATE_WEBLOGIC_DOMAIN_INPUTS_YAML = "create-weblogic-domain-inputs.yaml";
  private static final String DOMAIN_CUSTOM_RESOURCE_YAML = "domain-custom-resource.yaml";
  private static final String CREATE_WEBLOGIC_DOMAIN_JOB_YAML = "create-weblogic-domain-job.yaml";
  private static final String TRAEFIK_YAML = "traefik.yaml";
  private static final String TRAEFIK_SECURITY_YAML = "traefik-security.yaml";
  private static final String WEBLOGIC_DOMAIN_PERSISTENT_VOLUME_YAML = "weblogic-domain-persistent-volume.yaml";
  private static final String WEBLOGIC_DOMAIN_PERSISTENT_VOLUME_CLAIM_YAML = "weblogic-domain-persistent-volume-claim.yaml";

  private Path userProjectsPath;
  private CreateDomainInputs inputs;

  public DomainFiles(Path userProjectsPath, CreateDomainInputs inputs) {
    this.userProjectsPath = userProjectsPath;
    this.inputs = inputs;
  }

  public Path userProjectsPath() { return userProjectsPath; }

  public Path getCreateWeblogicDomainInputsYamlPath() {
    return getWeblogicDomainPath().resolve(CREATE_WEBLOGIC_DOMAIN_INPUTS_YAML);
  }

  public Path getCreateWeblogicDomainJobYamlPath() {
    return getWeblogicDomainPath().resolve(CREATE_WEBLOGIC_DOMAIN_JOB_YAML);
  }

  public Path getDomainCustomResourceYamlPath() {
    return getWeblogicDomainPath().resolve(DOMAIN_CUSTOM_RESOURCE_YAML);
  }

  public Path getTraefikYamlPath() {
    return getWeblogicDomainPath().resolve(TRAEFIK_YAML);
  }

  public Path getTraefikSecurityYamlPath() {
    return getWeblogicDomainPath().resolve(TRAEFIK_SECURITY_YAML);
  }

  public Path getWeblogicDomainPersistentVolumeYamlPath() {
    return getWeblogicDomainPath().resolve(WEBLOGIC_DOMAIN_PERSISTENT_VOLUME_YAML);
  }

  public Path getWeblogicDomainPersistentVolumeClaimYamlPath() {
    return getWeblogicDomainPath().resolve(WEBLOGIC_DOMAIN_PERSISTENT_VOLUME_CLAIM_YAML);
  }

  public Path getWeblogicDomainPath() {
    return userProjectsPath().resolve("weblogic-domains").resolve(inputs.getDomainUID());
  }

  public List<Path> getExpectedContents(boolean includeDirectory) {
    List<Path> rtn = new ArrayList();
    rtn.add(getCreateWeblogicDomainInputsYamlPath());
    rtn.add(getCreateWeblogicDomainJobYamlPath());
    rtn.add(getDomainCustomResourceYamlPath());
    rtn.add(getTraefikYamlPath());
    rtn.add(getTraefikSecurityYamlPath());
    rtn.add(getWeblogicDomainPersistentVolumeYamlPath());
    rtn.add(getWeblogicDomainPersistentVolumeClaimYamlPath());
    if (includeDirectory) {
      rtn.add(getWeblogicDomainPath());
    }
    return rtn;
  }
}
