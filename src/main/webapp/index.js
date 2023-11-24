/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


$(function() {
  var cities = [
    // List of 63 provinces/cities as autocomplete options
"An Giang", "Ba Ria - Vung Tau","Da Lat", "Bac Giang", "Bac Kan", "Bac Lieu", "Bac Ninh", "Ben Tre", "Binh Dinh", "Binh Duong", "Binh Phuoc", "Binh Thuan", "Ca Mau", "Cao Bang", "Dak Lak", "Dak Nong", "Dien Bien", "Dong Nai", "Dong Thap", "Gia Lai", "Ha Giang", "Ha Nam", "Ha Tinh", "Hai Duong", "Hau Giang", "Hoa Binh", "Hung Yen", "Khanh Hoa", "Kien Giang", "Kon Tum", "Lai Chau", "Lam Dong", "Lang Son", "Lao Cai", "Long An", "Nam Dinh", "Nghe An", "Ninh Binh", "Ninh Thuan", "Phu Tho", "Quang Binh", "Quang Nam", "Quang Ngai", "Quang Ninh", "Quang Tri", "Soc Trang", "Son La", "Tay Ninh", "Thai Binh", "Thai Nguyen", "Thanh Hoa", "Thua Thien Hue", "Tien Giang", "Tra Vinh", "Tuyen Quang", "Vinh Long", "Vinh Phuc", "Yen Bai", "Phu Yen", "Can Tho", "Da Nang", "Hai Phong", "Ha Noi", "Ho Chi Minh"  ];

  // Initialize autocomplete for departure field
  $("#fromlocation").on("focus", function() {
    $(this).autocomplete({
      source: cities
    }).autocomplete("search", "");
  });

  // Initialize autocomplete for destination field
  $("#tolocation").on("focus", function() {
    $(this).autocomplete({
      source: cities
    }).autocomplete("search", "");
  });
});

// MESSAGE INPUT
const textarea = document.querySelector('.chatbox-message-input')
const chatboxForm = document.querySelector('.chatbox-message-form')

textarea.addEventListener('input', function () {
	let line = textarea.value.split('\n').length

	if(textarea.rows < 6 || line < 6) {
		textarea.rows = line
	}

	if(textarea.rows > 1) {
		chatboxForm.style.alignItems = 'flex-end'
	} else {
		chatboxForm.style.alignItems = 'center'
	}
})



// TOGGLE CHATBOX
const chatboxToggle = document.querySelector('.chatbox-toggle')
const chatboxMessage = document.querySelector('.chatbox-message-wrapper')

chatboxToggle.addEventListener('click', function () {
	chatboxMessage.classList.toggle('show')
})



// DROPDOWN TOGGLE
const dropdownToggle = document.querySelector('.chatbox-message-dropdown-toggle')
const dropdownMenu = document.querySelector('.chatbox-message-dropdown-menu')

dropdownToggle.addEventListener('click', function () {
	dropdownMenu.classList.toggle('show')
})

document.addEventListener('click', function (e) {
	if(!e.target.matches('.chatbox-message-dropdown, .chatbox-message-dropdown *')) {
		dropdownMenu.classList.remove('show')
	}
})




// CHATBOX MESSAGE
const chatboxMessageWrapper = document.querySelector('.chatbox-message-content')
const chatboxNoMessage = document.querySelector('.chatbox-message-no-message')

chatboxForm.addEventListener('submit', function (e) {
	e.preventDefault()

	if(isValid(textarea.value)) {
		writeMessage()
		setTimeout(autoReply, 1000)
	}
})



function addZero(num) {
	return num < 10 ? '0'+num : num
}

function writeMessage() {
	const today = new Date()
	let message = `
		<div class="chatbox-message-item sent">
			<span class="chatbox-message-item-text">
				${textarea.value.trim().replace(/\n/g, '<br>\n')}
			</span>
			<span class="chatbox-message-item-time">${addZero(today.getHours())}:${addZero(today.getMinutes())}</span>
		</div>
	`
	chatboxMessageWrapper.insertAdjacentHTML('beforeend', message)
	chatboxForm.style.alignItems = 'center'
	textarea.rows = 1
	textarea.focus()
	textarea.value = ''
	chatboxNoMessage.style.display = 'none'
	scrollBottom()
}

function autoReply() {
	const today = new Date()
	let message = `
		<div class="chatbox-message-item received">
			<span class="chatbox-message-item-text">
				Thank you for your awesome support!
			</span>
			<span class="chatbox-message-item-time">${addZero(today.getHours())}:${addZero(today.getMinutes())}</span>
		</div>
	`
	chatboxMessageWrapper.insertAdjacentHTML('beforeend', message)
	scrollBottom()
}

function scrollBottom() {
	chatboxMessageWrapper.scrollTo(0, chatboxMessageWrapper.scrollHeight)
}

function isValid(value) {
	let text = value.replace(/\n/g, '')
	text = text.replace(/\s/g, '')

	return text.length > 0
}
$(document).ready(function() {
        
        $('#fromlocation').on('input', function() {
            let query = $(this).val();
            if (query) {
                $.ajax({
                    type: 'GET',
                    url: '<%=request.getContextPath()%>/findlocation',
                    data: { fromLocation: query },
                    success: function(data) {
                        console.log(data);
                        populateSuggestions(data, 'from');
                    },
                    error: function(error) {
                        console.error("Error fetching data:", error);
                    }
                });
            } else {
                $('#suggestionBoxFrom').hide();
            }
        });

        $('#tolocation').on('input', function() {
            let query = $(this).val();
            if (query) {
                $.ajax({
                    type: 'GET',
                    url: '<%=request.getContextPath()%>/findlocation',
                    data: { toLocation: query },
                    success: function(data) {
                        console.log(data);
                        populateSuggestions(data, 'to');
                    },
                    error: function(error) {
                        console.error("Error fetching data:", error);
                    }
                });
            } else {
                $('#suggestionBoxTo').hide();
            }
        });
        // If you click outside the input and suggestion box, hide suggestions
        $(document).on('click', function(event) {
            if (!$(event.target).closest('#fromlocation').length && !$(event.target).closest('#suggestionBoxfrom').length) {
                $('#suggestionBoxfrom').hide();
            }
        });
        $(document).on('click', function(event) {
            if (!$(event.target).closest('#tolocation').length && !$(event.target).closest('#suggestionBoxto').length) {
                $('#suggestionBoxto').hide();
            }
        });

        var today = new Date().toISOString().split('T')[0];
        $("#datego").attr('min', today);
        $('#searchForm').on('submit', function (event){
            event.preventDefault();
           if(!validateForm()) {
               return;
           }
            $(this).off('submit').submit();
        });
    });

    function populateSuggestions(locations, choice) {
        let $suggestionBox = $('#suggestionBox' +choice);
        $suggestionBox.empty().show();

        locations.forEach(locationObj => {
            let $suggestion = $('<div class="suggestion"></div>').text(locationObj);
            $suggestion.on('click', function() {
                $('#'+choice+'location').val(locationObj);
                $suggestionBox.hide();
            });
            $suggestionBox.append($suggestion);
        });
    }
    function validateForm() {
        // Validate fromLocation and toLocation
        var fromLocation = document.getElementById("fromlocation").value;
        var toLocation = document.getElementById("tolocation").value;

        if (!fromLocation || !toLocation) {
            alert("Điểm đi và điểm đến không được trống.");
            return false;
        }

        // Validate dateGo is not smaller than today
        var dateGo = document.getElementById("datego").value;
        var today = new Date().toISOString().split('T')[0];
        if (dateGo < today) {
            alert("Ngày xuất hành không được nhỏ hơn ngày hiện tại.");
            return false;
        }

        // If all validations pass
        return true;
    }
