
class grails {
  exec { 'ppa:groovy-dev/grails':
    command => 'add-apt-repository ppa:groovy-dev/grails',
    unless => 'ls /etc/apt/sources.list.d/groovy-dev-grails-*',
  }

  exec { 'grails-apt-get-update':
    command => 'apt-get update',
    subscribe => Exec['ppa:groovy-dev/grails'],
    refreshonly => true,
  }

  package { 'grails':
    name => "grails-2.2.0",
    ensure => present,
  }

  Exec['grails-apt-get-update'] -> Package['grails']
}

node default {

	class { 'vagrant':}

	class  { 'java':
		distribution => 'jdk',
		version      => 'latest',
	}

	class {'grails':
		require => [Package[python-software-properties]]
	}

	package { 'python-software-properties':
		require => [Class[vagrant]],
    	ensure => present,
  	}

	package { "unzip":
	    ensure => "installed"
	}

	package { "curl":
	    ensure => "installed"
	}

	Exec { path => '/usr/bin:/bin:/usr/sbin:/sbin' }



}