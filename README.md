# DigiVol   [![Build Status](https://travis-ci.org/AtlasOfLivingAustralia/volunteer-portal.svg?branch=develop)](https://travis-ci.org/AtlasOfLivingAustralia/volunteer-portal)

The [Atlas of Living Australia], in collaboration with the [Australian Museum], developed [DigiVol]
to harness the power of online volunteers (also known as crowdsourcing) to digitise biodiversity data that is locked up
in biodiversity collections, field notebooks and survey sheets.

## Running

The ansible inventories are currently out of date.  You can run DigiVol manually by using gradle to build although these instructions don't describe how to get a CAS server running so you won't be able to start the app without that:

```bash
export dv_pgpass=password
docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=${dv_pgpass} -d postgres:9
cd <this git repo>
cat <<EOF > ./local.properties
flywayUrl=jdbc:postgresql://localhost:5432/digivol
flywayUsername=postgres
flywayPassword=${dv_pgpass}
EOF
psql -U postgres -h localhost -W postgres -c "CREATE DATABASE digivol" # run `echo ${dv_pgpass}` to see password
./gradlew assemble
mkdir -p /data/volunteer-portal/config/
cat <<EOF > /data/volunteer-portal/config/volunteer-portal-config.properties
dataSource.url=jdbc:postgresql://localhost:5432/digivol
dataSource.username=postgres
dataSource.password=${dv_pgpass}
EOF
# see https://github.com/AtlasOfLivingAustralia/ala-install/blob/master/ansible/roles/volunteer_portal/templates/config.properties for more config options
java -jar build/libs/volunteer-portal-*exec.jar
tail -n 30 logs/digivol.log # to see the CAS error
open http://localhost:8080/ # if you got past the CAS server problems
# to clean up
docker rm -f some-postgres
rm -r /data/volunteer*
```

~~To run up a vagrant instance of DigiVol you can use the volunteer_portal_instance ansible playbook from the
[AtlasOfLivingAustralia/ala-install] repository.  This will deploy a pre-compiled version from the ALA Maven repository.~~

~~*NOTE: Both [vagrant] and [ansible] must be installed first.*~~

~~Then setup the VM and run the playbook:~~

```bash
git clone https://github.com/AtlasOfLivingAustralia/ala-install.git
cd ala-install/vagrant/ubuntu-trusty
vagrant up
cd ../../ansible
ansible-playbook -i inventories/vagrant --user vagrant --private-key ~/.vagrant.d/insecure_private_key --sudo volunteer-portal.yml
```

~~Deploying to a server can be done similarly, though you will need to define an ansible inventory first.~~

##Contributing

DigiVol is a [Grails] v3.2.4 based web application.  It requires [PostgreSQL] for data storage.  Development follows the 
[git flow] workflow.

For git flow operations you may like to use the `git-flow` command line tools.  Either install [Atlassian SourceTree]
which bundles its own version or install them via:

```bash
# OS X
brew install git-flow
# Ubuntu
apt-get install git-flow
```

[Atlas of Living Australia]: http://www.ala.org.au/
[Australian Museum]: http://australianmuseum.net.au/
[PostgreSQL]: http://postgres.org/
[DigiVol]: http://volunteer.ala.org.au/
[Grails]: http://www.grails.org/
[git flow]: https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow "Gitflow Workflow"
[Atlassian SourceTree]: http://www.sourcetreeapp.com/
[AtlasOfLivingAustralia/ala-install]: https://github.com/AtlasOfLivingAustralia/ala-install
[vagrant]: https://www.vagrantup.com/
[ansible]: http://www.ansible.com/home
