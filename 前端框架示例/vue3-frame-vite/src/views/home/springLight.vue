<template>
  <div class="view">
    <div class="logo"></div>
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
    background-image: url('/src/assets/images/header/bxl-spring-boot.png'); /* 替换为实际的图标地址 */
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
    background: radial-gradient(circle, rgba(108, 181, 45, 0.6), transparent 60%);
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
    content: 'Spring';
    position: absolute;
    right: 22px; /* 距离右边缘50px */
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

