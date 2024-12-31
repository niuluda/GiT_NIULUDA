<template>
  <div>
    <textarea v-model="userInput" placeholder="请输入您的问题"></textarea>
    <button @click="callGPT">发送到u-- GPT</button>
    <div>
      <strong>GPT 回复:</strong>
      <p>{{ gptResponse }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userInput: '', // 用户输入
      gptResponse: '', // GPT 回复
      apiKey: '', // 替换为你的 OpenAI API 密钥
    };
  },
  methods: {
    async callGPT() {
      if (!this.userInput.trim()) {
        this.gptResponse = '请输入内容！';
        return;
      }

      try {
        const response = await axios.post(
          'https://api.openai.com/v1/chat/completions',
          {
            model: 'gpt-4o-mini', // 或 'gpt-4'
            messages: [{ role: 'developer', content: this.userInput }],
          },
          {
            headers: {
              'Content-Type': 'application/json',
              Authorization: `Bearer ${this.apiKey}`,
            },
          }
        );

        this.gptResponse = response.data.choices[0].message.content;
      } catch (error) {
        console.error('GPT API 调用失败:', error);
        this.gptResponse = '调用失败，请稍后重试。';
      }
    },
  },
};
</script>
