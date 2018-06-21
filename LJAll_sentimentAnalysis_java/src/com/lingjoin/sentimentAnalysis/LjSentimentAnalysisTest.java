package com.lingjoin.sentimentAnalysis;

import org.junit.Test;

import com.lingjoin.sentimentAnalysis.LJSentimentAnalysisLibrary.CLibrarySentimentAnalysis;

public class LjSentimentAnalysisTest {

	/**
	 * 测试导入自定义情感词典
	 */
	@Test
	public void testImportUserDict() {
		// 初始化
		int flag = CLibrarySentimentAnalysis.Instance.LJST_Inits(
				"sentimentAnalysisData", 1, "");
		if (flag == 0) {
			System.out.println("SentimentAnalysis初始化失败");
			System.exit(0);
		} else {
			System.out.println("SentimentAnalysis初始化成功");
		}

		// 导入词典
		int count = CLibrarySentimentAnalysis.Instance.LJST_ImportUserDict("dict/sentiment_lib.txt", true);
		System.out.println("count: " + count);
		CLibrarySentimentAnalysis.Instance.LJST_Exits();
	}

	/**
	 * 根据内容获得情感分析
	 */
	@Test
	public void testGetParagraphSent() {
		// 初始化
		int flag = CLibrarySentimentAnalysis.Instance.LJST_Inits(
				"sentimentAnalysisData", 1, "");
		if (flag == 0) {
			System.out.println("SentimentAnalysis初始化失败");
			System.exit(0);
		} else {
			System.out.println("SentimentAnalysis初始化成功");
		}

		// 根据内容获得情感分析
		byte[] resultByte = new byte[10000];
		String content = "太心痛了…曾经那么幸福的一家五口，那么可爱的孩子们…一场大火都消失的一无所有……而林爸爸的维权之路更是难中之难，心痛…… ​​​​";
		//		String content = "喜欢中山狼";
		CLibrarySentimentAnalysis.Instance.LJST_GetParagraphSent(content,
				resultByte);
		System.out.println("根据文本内容分析结果：\n" + new String(resultByte));// 输出分析结果

		// 退出
		CLibrarySentimentAnalysis.Instance.LJST_Exits();
	}

	/**
	 * 根据文本文件获得情感分析
	 */
	@Test
	public void testGetFileSent() {
		// 初始化
		int flag = CLibrarySentimentAnalysis.Instance.LJST_Inits("sentimentAnalysisData", 1, "");
		if (flag == 0) {
			System.out.println("SentimentAnalysis初始化失败");
			System.exit(0);
		} else {
			System.out.println("SentimentAnalysis初始化成功");
		}

		// 根据文本文件获得情感分析
		byte[] resultByte = new byte[10000];
		CLibrarySentimentAnalysis.Instance.LJST_GetFileSent("content.txt",
				resultByte);
		System.out.println("根据文件分析结果：" + new String(resultByte));// 输出分析结果

		// 退出
		CLibrarySentimentAnalysis.Instance.LJST_Exits();
	}
}
