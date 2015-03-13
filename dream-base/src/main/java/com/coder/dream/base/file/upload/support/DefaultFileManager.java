
package com.coder.dream.base.file.upload.support;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.coder.dream.base.file.upload.FileManager;
import com.coder.dream.base.file.upload.FileStoreStrategy;
import com.coder.dream.base.utils.FileUtil;

public class DefaultFileManager implements FileManager{
	
	private String[] defaultSupportSuffixs;
	
	private String[] supportSuffixs;
	
	private FileStoreStrategy fileStoreStrategy;
	
	public DefaultFileManager(){
		defaultSupportSuffixs = new String[]{".png"};
		supportSuffixs = defaultSupportSuffixs;
	}

	public List<File> write(MultipartFile... writingFile) throws IOException {
		if(writingFile == null || writingFile.length == 0) return null;

		List<File> files = new ArrayList<File>();
 		for (int i = 0 ; i < writingFile.length; ++i) {
 			File writedFile = writeOne(writingFile[i]);
 			if(writedFile != null) files.add(writedFile);
		}
		return files;
	}

	private File writeOne(MultipartFile multipartFile) throws IOException{
		File writedFile = null;
		if (!multipartFile.isEmpty()) {
			//校验
			String sourceName = multipartFile.getOriginalFilename();
			String fileSuffix = FileUtil.getFileSuffix(sourceName);
			
			boolean supportSuffix = isSupportSuffix(fileSuffix);
			if(!supportSuffix){
				throw new RuntimeException("不支持后缀为"+fileSuffix+"的文件存储!");
			}
			
			InputStream inputStream = null;
			String dir = fileStoreStrategy.getStoreDir();
			File fileDir = new File(dir);
			if (!fileDir.exists()) {
				FileUtils.forceMkdir(fileDir);
			}

			String desName = fileStoreStrategy.getStoreFileName(sourceName);
			String desPathName = dir + File.separator + desName;
			writedFile = new File(desPathName);

			inputStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(inputStream, writedFile);
		}
		return writedFile;
	}
	
	public void setFileStoreStrategy(FileStoreStrategy strategy) {
		this.fileStoreStrategy = strategy;
	}

	public FileStoreStrategy getFileStoreStrategy() {
		return fileStoreStrategy;
	}

	public boolean canResetSupportSuffixs() {
		return true;
	}

	public String[] getSupportSuffixs() {
		return supportSuffixs;
	}

	public String[] appendSupportSuffixs(String[] appendSuffixs) {
		if(appendSuffixs == null || appendSuffixs.length == 0) return supportSuffixs;
		if(supportSuffixs == null){
			setSupportSuffixs(appendSuffixs);
		}
		Set<String> set = new HashSet<String>();
		CollectionUtils.mergeArrayIntoCollection(supportSuffixs, set);
		CollectionUtils.mergeArrayIntoCollection(appendSuffixs, set);
		String[] newSupportSuffixs = new String[set.size()];
		newSupportSuffixs = set.toArray(newSupportSuffixs);
		setSupportSuffixs(newSupportSuffixs);
		return supportSuffixs;
	}

	public void setSupportSuffixs(String[] suffixs) {
		if(suffixs == null || suffixs.length == 0) return ;
		supportSuffixs = suffixs;
	}

	public boolean isSupportSuffix(String suffix) {
		return Arrays.binarySearch(supportSuffixs, suffix) >= 0;
	}


}

