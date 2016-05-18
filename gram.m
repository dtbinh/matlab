function out = gram(A)

out = A;

for i = 1:size(A, 2)
    out(:,i) = A(:,i)
end

end
