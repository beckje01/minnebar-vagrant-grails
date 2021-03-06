# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant::Config.run do |config|

  config.vm.define :grailsService do |web_config|

    web_config.vm.box = "ubuntu12"

    # Assign this VM to a host only network IP, allowing you to access it
    # via the IP.
    web_config.vm.network :hostonly, "10.0.10.30"

    # Configure DNS according with the new version of vagrant
    web_config.vm.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]

    config.vm.share_folder "minnebar", "~/minnebar", "./minnebar"


    web_config.vm.provision :puppet do |puppet|
      puppet.options = ["--verbose","--debug"]
      puppet.module_path    = "grails/modules"
      puppet.manifests_path = "grails/manifests"
      puppet.manifest_file  = "base.pp"

    end


    # Forward a port from the guest to the host, which allows for outside
    # computers to access the VM, whereas host only networking does not.
    web_config.vm.forward_port 8080, 8090
  end

    config.vm.define :redisServer do |redis_config|

      redis_config.vm.box = "ubuntu12"

      redis_config.vm.network :hostonly, "10.0.10.31"

      redis_config.vm.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
      
      redis_config.vm.provision :chef_solo do |chef|
        chef.cookbooks_path = "redis/chef/cookbooks"
        chef.roles_path = "redis/chef/roles"
        chef.data_bags_path = "redis/chef/data_bags"
        chef.add_recipe "redis::source"
        chef.add_recipe "redis::master"

        # You may also specify custom JSON attributes:
        chef.json = { :redis => { :version => "2.6.7", :appendonly => "yes" } }
      end
    end
end
