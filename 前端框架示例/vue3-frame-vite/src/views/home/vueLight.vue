<template>
  <div class="view">
    <div class="logo" onclick="window.location.href='https://github.com/niuluda/GiT_NIULUDA/tree/main/vue%E4%BD%BF%E7%94%A8%E6%95%99%E7%A8%8B/echarts%E5%A6%82%E4%BD%95%E5%8A%A8%E6%80%81%E6%94%B9%E5%8F%98%E5%B0%BA%E5%AF%B8'"></div>
  </div>
</template>

<script setup>

</script>

<style scoped>
  .view {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
  }

  .logo {
    position: relative;
    width: 150px;
    height: 150px;
    background-image: url('/src/assets/images/header/Vue01.png'); /* 替换为实际的图标地址 */
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center;
    transition: all 0.3s ease;
    z-index: 2;
  }

  .logo::before {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background: radial-gradient(circle, rgba(65, 184, 131, 0.6), transparent 60%);
    filter: blur(10px);
    opacity: 0; /* 初始状态光晕隐藏 */
    transition: opacity 0.6s ease, transform 0.6s ease;
    z-index: -1;
  }

  .logo:hover::before {
    opacity: 1; /* 悬浮时显示光晕 */
    transform: translate(-50%, -50%) scale(1.4); /* 光晕放大效果 */
  }

  :deep(.logo::after){
    content: 'Vue';
    position: absolute; /* 使文字固定在容器的下方 */
    /*bottom: -36px; !* 距离下边缘 30px *!*/
    /*left: 50%;*/
    /*transform: translateX(-50%); !* 居中显示 *!*/
    color: #ffffff;

  }
  :deep(.logo::after){
    content: 'Vue.js';
    position: absolute;
    right: 34px; /* 距离右边缘50px */
    top: 50%;
    transform: translateY(-50%); /* 垂直居中 */
    font-size: 10px;
    color: #ffffff;

  }
  /*脉冲效果*/
  @keyframes pulse {
    0% {
      transform: translate(-50%, -50%) scale(1);
    }
    50% {
      transform: translate(-50%, -50%) scale(1.3);
    }
    100% {
      transform: translate(-50%, -50%) scale(1);
    }
  }
  /*应用脉冲效果*/
  .logo:hover::before {
    /*animation: pulse 1.5s infinite;*/
  }
</style>

