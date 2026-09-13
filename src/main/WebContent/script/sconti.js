let currentSlide = 0;
        
        function moveSlide(direction) {
            const slider = document.getElementById('promoSlider');
            const slides = document.querySelectorAll('.slide');
            if (slides.length === 0) return;
            
            const totalSlides = slides.length;
            currentSlide = (currentSlide + direction + totalSlides) % totalSlides;
            slider.style.transform = `translateX(-${currentSlide * 100}%)`;
        }
        setInterval(() => moveSlide(1), 7000)