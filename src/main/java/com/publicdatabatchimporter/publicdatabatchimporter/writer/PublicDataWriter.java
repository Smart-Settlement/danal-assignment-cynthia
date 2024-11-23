package com.publicdatabatchimporter.publicdatabatchimporter.writer;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.publicdatabatchimporter.publicdatabatchimporter.dto.PublicDataDTO;
import com.publicdatabatchimporter.publicdatabatchimporter.model.PublicData;
import com.publicdatabatchimporter.publicdatabatchimporter.repository.PublicDataRepository;

// Writer 구현
@Component
public class PublicDataWriter implements ItemWriter<PublicData> {

	@Autowired
	private PublicDataRepository publicDataRepository; // JPA Repository

	// 생성자 주입
	public PublicDataWriter(PublicDataRepository publicDataRepository) {
		this.publicDataRepository = publicDataRepository;
	}

	@Override
	public void write(Chunk<? extends PublicData> chunk) throws Exception {
		// Chunk를 List로 변환하여 저장
		List<? extends PublicData> items = chunk.getItems();
		System.out.println("Saving chunk of data: " + items.size());
		publicDataRepository.saveAll(items); // JPA의 saveAll 메서드로 저장
	}
}
