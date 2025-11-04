package com.ducnh.syncfilekafka;

import java.io.File;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ducnh.syncfilekafka.services.KafkaConsumeService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RunConsumerKafkaService implements CommandLineRunner {
	
	private final KafkaConsumeService consumerService;

    @Override
    public void run(String...args) throws Exception {
    	
    	String domain = "MITALAB";
		String username = "administrator";
		String password = "Fbo@123";
	    StaticUserAuthenticator auth = new StaticUserAuthenticator(domain, username, password);
	    FileSystemOptions opts = new FileSystemOptions();
	    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
	    System.out.println(DefaultFileSystemConfigBuilder.getInstance().getUserAuthenticator(opts));
	    
	    FileSystemManager manager = VFS.getManager();
	    
	    
	    FileObject local = manager.resolveFile(
	    		"\\\\192.168.100.53\\MinhAn ERP\\Upload\\MINHAN_Test_App\\e118eac10098420790f51b94699a2127" );
	    System.out.println(local.getPublicURIString());
	    System.out.println(local.exists());
	    FileObject remote = manager.resolveFile(
	    		"\\\\192.168.5.11\\NamPhuong\\Upload\\NamPhuong_App\\e118eac10098420790f51b94699a2127");
	    System.out.println(remote.exists());
        consumerService.consumeMessage();
    }
}
